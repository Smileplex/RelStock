import React from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';
import PropTypes from 'prop-types';
import injectReducer from 'utils/injectReducer';
import injectSaga from 'utils/injectSaga';
import { slide as Menu } from 'react-burger-menu';
import { loadSearchedStocksRequest } from '../StockMap/actions';
import { loadTopSearchedStocksRequest } from './actions';
import reducer from './reducer';
import saga from './saga';
import SearchBody from '../../components/Search/SearchBody';
import makeTopSearchedStocks from './selectors';

class SearchSite extends React.PureComponent {
  onSearch = input => {
    const { onLoadSearchedStocks } = this.props;
    onLoadSearchedStocks(input);
  };

  componentDidMount() {
    const { onLoadTopSearchedStocks } = this.props;
    onLoadTopSearchedStocks();
  }

  render() {
    return (
      <Menu
        width="375px"
        burgerButtonClassName="fas fa-search search"
        customBurgerIcon={<i className="fas fa-search" />}
      >
        <SearchBody
          topSearchedStocks={this.props.topSearchedStocks}
          onSearch={this.onSearch}
        />
      </Menu>
    );
  }
}

SearchSite.propTypes = {
  topSearchedStocks: PropTypes.object,
  onLoadSearchedStocks: PropTypes.func,
  onLoadTopSearchedStocks: PropTypes.func,
};

export function mapDispatchToProps(dispatch) {
  return {
    onLoadSearchedStocks: keyword =>
      dispatch(loadSearchedStocksRequest(keyword)),

    onLoadTopSearchedStocks: () => dispatch(loadTopSearchedStocksRequest()),
  };
}

const mapStateToProps = createStructuredSelector({
  topSearchedStocks: makeTopSearchedStocks,
});

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'search', reducer });
const withSaga = injectSaga({ key: 'search', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(SearchSite);
