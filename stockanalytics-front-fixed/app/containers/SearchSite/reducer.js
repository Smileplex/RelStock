import { List, Map } from 'immutable/dist/immutable';
import { LOAD_TOP_SEARCHED_STOCKS_SUCCESS } from './constants';

export const initialState = Map({
  topSearchedStocks: List(),
});

function stockReducer(state = initialState, action) {
  switch (action.type) {
    case LOAD_TOP_SEARCHED_STOCKS_SUCCESS:
      return state.set('topSearchedStocks', action.data);
    default:
      return state;
  }
}

export default stockReducer;
