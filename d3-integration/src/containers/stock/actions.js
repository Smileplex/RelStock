import createAction from "redux-actions/es/createAction";
import {
    LOAD_DEFAULT_STOCKS,
    LOAD_DEFAULT_STOCKS_REQUEST,
    LOAD_SEARCHED_STOCKS
} from './constants';

export const loadDefaultStocks = createAction(LOAD_DEFAULT_STOCKS, data => data);
export const loadDefaultStocksRequest = createAction(LOAD_DEFAULT_STOCKS_REQUEST);
export const loadSearchedStocks = createAction(LOAD_SEARCHED_STOCKS, data => data);


