import {
  LOAD_TOP_SEARCHED_STOCKS_REQUEST,
  LOAD_TOP_SEARCHED_STOCKS_SUCCESS,
} from './constants';

export function loadTopSearchedStocksRequest() {
  return {
    type: LOAD_TOP_SEARCHED_STOCKS_REQUEST,
  };
}

export function loadTopSearchedStocksSuccess(data) {
  return {
    type: LOAD_TOP_SEARCHED_STOCKS_SUCCESS,
    data,
  };
}
