import {List, Map} from "immutable/dist/immutable";
import handleActions from "redux-actions/es/handleActions";
import {
    LOAD_DEFAULT_STOCKS,
    LOAD_DEFAULT_STOCKS_REQUEST,
    LOAD_SEARCHED_STOCKS
} from './constants';

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
