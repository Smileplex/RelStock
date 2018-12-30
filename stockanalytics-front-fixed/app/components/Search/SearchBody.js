import React from 'react';
import PropTypes from 'prop-types';

class SearchBody extends React.PureComponent {
  onKeyPressed = e => {
    const code = e.keyCode ? e.keyCode : e.which;
    if (code === 13) {
      e.preventDefault();

      const { onSearch } = this.props;
      onSearch(e.target.value);
    }
  };

  render() {
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
                <tr>
                  <td>종목</td>
                  <td className="value now">270,000</td>
                  <td className="value up">
                    <i className="fas fa-caret-up" />
                    5,000
                  </td>
                </tr>
                <tr>
                  <td>키워드</td>
                  <td />
                  <td />
                </tr>
                <tr>
                  <td>키워드</td>
                  <td />
                  <td />
                </tr>
                <tr>
                  <td>종목</td>
                  <td className="value now">21,900</td>
                  <td className="value up">
                    <i className="fas fa-caret-up" />
                    500
                  </td>
                </tr>
                <tr>
                  <td>종목</td>
                  <td className="value now">23,700</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    700
                  </td>
                </tr>
                <tr>
                  <td>종목</td>
                  <td className="value now">87,900</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    2,400
                  </td>
                </tr>
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
                <tr>
                  <td>NAVER</td>
                  <td className="value now">770,000</td>
                  <td className="value up">
                    <i className="fas fa-caret-up" />
                    5,000
                  </td>
                </tr>
                <tr>
                  <td>삼성전자</td>
                  <td className="value now">47,450</td>
                  <td className="value up">
                    <i className="fas fa-caret-up" />
                    550
                  </td>
                </tr>
                <tr>
                  <td>삼영전자</td>
                  <td className="value now">14,650</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    500
                  </td>
                </tr>
                <tr>
                  <td>LG디스플레이</td>
                  <td className="value now">21,950</td>
                  <td className="value up">
                    <i className="fas fa-caret-up" />
                    500
                  </td>
                </tr>
                <tr>
                  <td>한일철강</td>
                  <td className="value now">23,730</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    700
                  </td>
                </tr>
                <tr>
                  <td>SK하이닉스</td>
                  <td className="value now">87,900</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    2,400
                  </td>
                </tr>
                <tr>
                  <td>티에이치엔</td>
                  <td className="value now">2,220</td>
                  <td className="value">-</td>
                </tr>
                <tr>
                  <td>LG화학</td>
                  <td className="value now">332,550</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    3,500
                  </td>
                </tr>
                <tr>
                  <td>동원산업</td>
                  <td className="value now">321,500</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    6,500
                  </td>
                </tr>
                <tr>
                  <td>조선선재</td>
                  <td className="value now">79,700</td>
                  <td className="value down">
                    <i className="fas fa-caret-down" />
                    1,600
                  </td>
                </tr>
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
};
export default SearchBody;
