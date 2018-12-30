import React from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';
import PropTypes from 'prop-types';
import injectReducer from 'utils/injectReducer';
import injectSaga from 'utils/injectSaga';
import { slide as Menu } from 'react-burger-menu';
import { loadSearchedStocksRequest } from '../StockMap/actions';
import reducer from './reducer';
import saga from './saga';
import SearchBody from '../../components/Search/SearchBody';
import makeSelectStocks from './selectors';

class SearchSite extends React.PureComponent {
  componentDidMount() {}

  onSearch = input => {
    const { onLoadSearchedStocks } = this.props;
    onLoadSearchedStocks(input);
  };

  render() {
    return (
      <Menu
        width="375px"
        burgerButtonClassName="fas fa-search search"
        customBurgerIcon={<i className="fas fa-search" />}
      >
        <SearchBody onSearch={this.onSearch} />
      </Menu>
    );
  }
}

SearchSite.propTypes = {
  onLoadSearchedStocks: PropTypes.func,
};

export function mapDispatchToProps(dispatch) {
  return {
    onLoadSearchedStocks: keyword =>
      dispatch(loadSearchedStocksRequest(keyword)),
  };
}

const mapStateToProps = createStructuredSelector({
  stocks: makeSelectStocks,
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
