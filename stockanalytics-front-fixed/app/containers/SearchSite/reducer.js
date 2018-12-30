import { List, Map } from 'immutable/dist/immutable';
import { LOAD_DEFAULT_STOCKS_SUCCESS } from './constants';

export const initialState = Map({
  stocks: List(),
});

function stockReducer(state = initialState, action) {
  switch (action.type) {
    case LOAD_DEFAULT_STOCKS_SUCCESS:
      return state.set('stocks', action.data);
    default:
      return state;
  }
}

export default stockReducer;
