import { createSelector } from 'reselect';
import { initialState } from './reducer';

const selectTopSearchedStocks = state => state.get('search', initialState);
const makeTopSearchedStocks = createSelector(
  selectTopSearchedStocks,
  searchStock => searchStock.get('topSearchedStocks'),
);

export default makeTopSearchedStocks;
