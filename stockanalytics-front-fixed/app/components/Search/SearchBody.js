import React from 'react';
import PropTypes from 'prop-types';
import { List } from 'immutable';

class SearchBody extends React.PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      recentSearchedStocks: List(),
    };
  }

  onKeyPressed = e => {
    const code = e.keyCode ? e.keyCode : e.which;
    if (code === 13) {
      e.preventDefault();

      const { onSearch } = this.props;
      const input = e.target.value;
      onSearch(input);
      this.setState(({ recentSearchedStocks }) => ({
        recentSearchedStocks: recentSearchedStocks.insert(0, input),
      }));
    }
  };

  render() {
    const { recentSearchedStocks } = this.state;
    const { topSearchedStocks } = this.props;
    return (
      <div className="search-body">
        <div className="menu-header">
          <input
            className="search-box"
            placeholder="검색하실 종목 또는 키워드를 입력하세요"
            onKeyDown={this.onKeyPressed}
          />
        </div>
        <div className="content">
          <div className="recent-search list">
            <span className="sub-header">최근검색</span>
            <table>
              <thead>
                <th width="35%" />
                <th width="34%" />
                <th />
              </thead>
              <tbody>
                {recentSearchedStocks.slice(0, 10).map(item => (
                  <tr>
                    <td>{item}</td>
                    <td className="value now">270,000</td>
                    <td className="value up">
                      <i className="fas fa-caret-up" />
                      5,000
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <hr />
          <div className="hot-keyword list">
            <span className="sub-header">인기검색어</span>
            <table>
              <thead>
                <th width="35%" />
                <th width="34%" />
                <th />
              </thead>
              <tbody>
                {topSearchedStocks.slice(0, 15).map(item => (
                  <tr>
                    <td>{item.name}</td>
                    <td className="value now">{item.price}</td>
                    {item.fluctRate.includes('+') ? (
                      <td className="value up">
                        <i className="fas fa-caret-up" />
                        {item.fluct}
                      </td>
                    ) : (
                      <td className="value down">
                        <i className="fas fa-caret-down" />
                        {item.fluct}
                      </td>
                    )}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <hr />
          <div className="stock-market list">
            <table>
              <tbody>
                <tr>
                  <td>코스피</td>
                  <td className="value up">2,239.19</td>
                  <td className="value up">
                    <i className="fas fa-caret-up" />
                    6.90(+0.30%)
                  </td>
                </tr>
                <br />
                <tr>
                  <td>코스닥</td>
                  <td className="value down">791.61</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    4.88(-0.61%)
                  </td>
                </tr>
                <br />
                <tr>
                  <td>코스피200</td>
                  <td className="value up">295.95</td>
                  <td className="value up">
                    <i className="fas fa-caret-up" />
                    1.05(+0.35%)
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

SearchBody.propTypes = {
  onSearch: PropTypes.func,
  topSearchedStocks: PropTypes.object,
};
export default SearchBody;
