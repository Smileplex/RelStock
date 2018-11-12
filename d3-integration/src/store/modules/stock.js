import {createAction, handleActions} from 'redux-actions';
import {List, Map} from "immutable";

const LOAD_DEFAULT_STOCKS = 'stock/LOAD_DEFAULT_STOCKS';
const LOAD_DEFAULT_STOCKS_REQUEST = 'stock/LOAD_DEFAULT_STOCKS_REQUEST';
const LOAD_SEARCHED_STOCKS = 'stock/LOAD_SEARCHED_STOCKS';

export const loadDefaultStocks = createAction(LOAD_DEFAULT_STOCKS, data => data);
export const loadDefaultStocksRequest = createAction(LOAD_DEFAULT_STOCKS_REQUEST);
export const loadSearchedStocks = createAction(LOAD_SEARCHED_STOCKS, data => data);

const initialState = Map({
    stocks : List()
});

export default handleActions({
    [LOAD_DEFAULT_STOCKS]: (state, action) => {
        return state.set('stocks', action.payload);
    },
    [LOAD_SEARCHED_STOCKS]: (state, action) => {
        return state.set('stocks', action.payload);
    }
}, initialState);

