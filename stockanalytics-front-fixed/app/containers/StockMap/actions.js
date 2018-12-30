import {
  LOAD_DEFAULT_STOCKS_REQUEST,
  LOAD_DEFAULT_STOCKS_SUCCESS,
  LOAD_SEARCHED_STOCKS_REQUEST,
  LOAD_SEARCHED_STOCKS_SUCCESS,
} from './constants';

export function loadDefaultStocksRequest() {
  return {
    type: LOAD_DEFAULT_STOCKS_REQUEST,
  };
}

export function loadDefaultStocksSuccess(data) {
  return {
    type: LOAD_DEFAULT_STOCKS_SUCCESS,
    data,
  };
}

export function loadSearchedStocksRequest(keyword) {
  return {
    type: LOAD_SEARCHED_STOCKS_REQUEST,
    keyword,
  };
}

export function loadSearchedStocksSuccess(data) {
  return {
    type: LOAD_SEARCHED_STOCKS_SUCCESS,
    data,
  };
}
