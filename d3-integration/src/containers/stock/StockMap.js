import React from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {height, width} from "../../constants";
import StockNodes from "../../components/StockNodes";
import * as stockActions from "../../store/modules/stock";

class StockMap extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        const {StockActions} = this.props;
        StockActions.loadDefaultStocksRequest();
    }

    shouldComponentUpdate(nextProps, nextState) {
        return this.props.stocks !== nextProps.stocks;
    }

    render() {
        const center = {x: width / 2, y: height / 2};
        const forceStrength = 0.03;
        return (
            <svg width={width} height={height}>
                <StockNodes data={this.props.stocks} forceStrength={forceStrength} center={center}/>
            </svg>
        )
    }
}

export default connect(
    (state) => ({
        stocks: state.stock.get('stocks')
    }),
    (dispatch) => ({
        StockActions: bindActionCreators(stockActions, dispatch)
    }))(StockMap);


