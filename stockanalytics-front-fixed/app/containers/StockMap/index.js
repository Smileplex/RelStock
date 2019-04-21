import React from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { createStructuredSelector } from 'reselect';
import injectReducer from 'utils/injectReducer';
import injectSaga from 'utils/injectSaga';
import { height, width } from './constants';
import StockNodes from '../../components/StockNode/StockNodes';
import { loadDefaultStocksRequest } from './actions';
import reducer from './reducer';
import saga from './saga';
import makeSelectStocks from './selectors';
import { joinRoom } from '../ChatSite/actions';

class StockMap extends React.PureComponent {
  componentDidMount() {
    const { onLoadDefaultStocks } = this.props;
    onLoadDefaultStocks();
  }

  render() {
    const center = { x: width / 2, y: height / 2 };
    const forceStrength = 0.15;
    return (
      <svg width={width} height={height}>
        <StockNodes
          data={this.props.stocks}
          onJoinRoom={this.props.onJoinRoom}
          forceStrength={forceStrength}
          center={center}
        />
      </svg>
    );
  }
}

StockMap.propTypes = {
  stocks: PropTypes.array,
  onLoadDefaultStocks: PropTypes.func,
  onJoinRoom: PropTypes.func,
};

export function mapDispatchToProps(dispatch) {
  return {
    onLoadDefaultStocks: () => dispatch(loadDefaultStocksRequest()),
    onJoinRoom: targetRoom => dispatch(joinRoom(targetRoom)),
  };
}

const mapStateToProps = createStructuredSelector({
  stocks: makeSelectStocks,
});

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'stock', reducer });
const withSaga = injectSaga({ key: 'stock', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(StockMap);
