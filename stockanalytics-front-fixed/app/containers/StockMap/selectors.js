/**
 * Homepage selectors
 */

import { createSelector } from 'reselect';
import { initialState } from './reducer';

const selectStock = state => state.get('stock', initialState);
const makeSelectStocks = createSelector(selectStock, stockState =>
  stockState.get('stocks'),
);

export default makeSelectStocks;
