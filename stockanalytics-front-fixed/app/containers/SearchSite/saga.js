import { takeLatest, call, put } from 'redux-saga/effects';
import * as d3 from 'd3';
import { LOAD_DEFAULT_STOCKS_REQUEST } from './constants';
import { loadDefaultStocksSuccess } from './actions';
import request from '../../utils/request';

export function* loadDefaultStocks() {
  const requestUrl = 'http://localhost:8000/search/stockkeyword/lg전자/2depth';
  const result = yield call(request, requestUrl);
  yield put(loadDefaultStocksSuccess(formatToD3(result)));
}

function formatToD3(result) {
  const convertedData = result.nodes.map(d => d.properties);
  const maxAmount = d3.max(convertedData, d => +d.price);
  const mean = d3.mean(convertedData, d => +d.price);
  const radiusScale = d3
    .scaleLinear()
    .range([100, 130])
    .domain([0, maxAmount]);

  const test = result.nodes.map(data => ({
    ...data.properties,
    radius:
      data.label === 'Stock'
        ? radiusScale(+data.properties.price)
        : radiusScale(mean),
    prevRadius:
      data.label === 'Stock'
        ? radiusScale(+data.properties.price)
        : radiusScale(mean),
    x: Math.random() * 900,
    y: Math.random() * 800,
    type: data.label,
    arrow:
      data.properties.riseFall && data.properties.riseFall === 2
        ? 'up'
        : 'down',
  }));

  return test;
}

export default function* defaultSaga() {
  yield takeLatest(LOAD_DEFAULT_STOCKS_REQUEST, loadDefaultStocks);
}
