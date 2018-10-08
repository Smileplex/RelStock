import React, { Component } from 'react';
import './App.css';
import StockMap from './components/StockMap'
import StockNodes from './components/StockNodes'
import {width, height} from './constants';
class App extends Component {
  render() {
    return (
      <div className="App">
        <StockMap width={width} height={height}>
            <StockNodes/>
        </StockMap>
      </div>
    );
  }
}

export default App;
