import React from 'react';
import * as d3 from 'd3'
class StockNodes extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            g: null
        };
    }

    onRef = (ref) => {
        this.setState({
            g: d3.select(ref)
        }, () => this.renderStockNodes(this.props.data))
    }

    shouldComponentUpdate(){
        return false
    }

    renderStockNodes= (data) => {
        const stockNodes = this.state.g.selectAll('.bubble').data(data, d => d.id);
        stockNodes.exit().remove();

        const newStockNodes = stockNodes.enter().append('circle').classed('bubble', true)
    }

    render(){
        return(
            <g ref={this.onRef} className="StockNodes"/>
        )
    }
}

export default StockNodes;
