import {createAction, handleActions} from 'redux-actions';
import {Map, List, fromJS} from "immutable";
import * as d3 from "d3";

const LOAD_DEFAULT_STOCKS = 'stock/LOAD_DEFAULT_STOCKS';
const LOAD_SEARCHED_STOCKS = 'stock/LOAD_SEARCHED_STOCKS';

export const loadDefaultStocks = createAction(LOAD_DEFAULT_STOCKS, data => data);
export const loadSearchedStocks = createAction(LOAD_SEARCHED_STOCKS, data => data);

const initialState = {
    stocks : List()
};

export default handleActions({
    [LOAD_DEFAULT_STOCKS]: (state, action) => {
        const stocks = fromJS(createNodes(action.payload));
        return state.update('stocks', stocks => stocks.pushAll(stocks));
    },
    [LOAD_SEARCHED_STOCKS]: (state, action) => {
        const stocks = fromJS(createNodes(action.payload));
        return state.update('stocks', stocks => stocks.pushAll(stocks));
    }
}, initialState);

export function createNodes(rawData){
    const maxAmount = d3.max(rawData, d=> +d.total_amount);

    const radiusScale = d3.scalePow().exponent(0.1).range([40, 100]).domain([0, maxAmount]);

    const myNodes = rawData.filter(d=>d.id).map(d => ({
        id: d.id,
        radius: radiusScale(+d.total_amount),
        prevRadius: radiusScale(+d.total_amount),
        value: +d.total_amount,
        name: d.grant_title,
        org: d.organization,
        group: d.group,
        year: d.start_year,
        x: Math.random() * 900,
        y: Math.random() * 800,
        type: d.type,
        code: d.code,
        price: d.price,
        fluct: d.fluct,
        fluctRate: d.fluctRate,
        arrow: d.fluct>0 ? "up" : "down"
    }))

    myNodes.sort((a,b)=> b.value = a.value);

    return myNodes;
}
