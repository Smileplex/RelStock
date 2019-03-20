import { takeLatest, call, put } from 'redux-saga/effects';
import { LOAD_TOP_SEARCHED_STOCKS_REQUEST } from './constants';
import { loadTopSearchedStocksSuccess } from './actions';
import request from '../../utils/request';

export function* loadTopSearchedStocks() {
  const requestUrl = 'http://localhost:8000/search/stock/topSearched';
  const result = yield call(request, requestUrl);
  yield put(loadTopSearchedStocksSuccess(result));
}

export default function* defaultSaga() {
  yield takeLatest(LOAD_TOP_SEARCHED_STOCKS_REQUEST, loadTopSearchedStocks);
}
