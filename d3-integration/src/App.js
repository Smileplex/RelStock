import React, {Component} from 'react';
import './App.css';
import * as d3 from 'd3';
import StockMap from './components/StockMap'
import StockNodes from './components/StockNodes'
import {createNodes} from './utils';
import {width, height} from './constants';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
            grouping: 'all'
        }
    }

    componentDidMount() {
        d3.csv('data/gates_money.csv', (err, data) => {
            if (err) {
                return;
            }
            this.setState({
                data: createNodes(data)
            })
        })
    }

    render() {
        const center = {x: width/2, y:height/2};
        const forceStrength =  0.03;
        return (
            <div className="App">
                <StockMap width={width} height={height}>
                    <StockNodes data={this.state.data} data2={this.state.data2} forceStrength={forceStrength} center={center}/>
                </StockMap>
            </div>
        );
    }
}

export default App;
