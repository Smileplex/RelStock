import React from 'react'
import {Map, List, fromJS} from 'immutable';
import * as d3 from "d3";
import {createNodes} from "../utils";
import {height, width} from "../constants";
import StockNodes from "../components/StockNodes"

class StockMap extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            data: [],
        }
    }

    componentDidMount() {
        d3.csv('data/gates_money.csv', (err, data) => {
            if (err) {
                return;
            }
            this.setState({
                data: createNodes(data)
            });
        })
    }

    render(){
        const center = {x: width/2, y:height/2};
        const forceStrength =  0.03;
        return (
            <svg width={width} height={height}>
                <StockNodes data={this.state.data} forceStrength={forceStrength} center={center}/>
            </svg>
        )
    }
}

//REDUX 연동
/*
loadDefaultStocks();
loadSearchedStocks();
 */
export default StockMap;
