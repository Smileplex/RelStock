import React from 'react';
import Collapsible from 'react-collapsible';
import PropTypes from 'prop-types';

class ChatRoom extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchString: '',
    };
  }

  handleChange = e => {
    this.setState({ searchString: e.target.value });
  };

  render() {
    const popularRooms = this.props.rooms.get('popularRooms');
    const stockRooms = this.props.rooms.get('stockRooms');
    const keywordRooms = this.props.rooms.get('keywordRooms');
    let totalRooms = stockRooms.merge(keywordRooms);
    const totalRoomCount = totalRooms.size;

    if (this.state.searchString.length > 0) {
      totalRooms = totalRooms.filter(v =>
        v.name.toLowerCase().match(this.state.searchString.toLowerCase()),
      );
    }

    return (
      <div className="chat-room">
        <div className="menu-header">
          채팅 <dim>{totalRoomCount}</dim>
        </div>
        <div className="content">
          <div className="search-bar">
            <i className="icon fa fa-search" />
            <input
              className="input-text"
              type="text"
              name="eingabe"
              placeholder="종목명 또는 키워드를 검색하세요"
              onChange={this.handleChange}
              value={this.state.searchString}
            />
          </div>
          <Collapsible
            open
            trigger={
              <div>
                <span>인기 채팅</span>
                <i className="fas fa-angle-down" />
              </div>
            }
            triggerWhenOpen={
              <div>
                <span>인기 채팅</span>
                <i className="fas fa-angle-up" />
              </div>
            }
          >
            <ul>
              {popularRooms.toList().map(({ name, size }) => (
                <li>
                  <button
                    type="button"
                    onClick={() =>
                      this.props.onJoinRoom(
                        JSON.stringify({
                          name,
                          type: 'Stock',
                        }),
                      )
                    }
                  >
                    {name} <span className="dim">({size})</span>
                  </button>
                </li>
              ))}
              {/* <li> */}
              {/* <button */}
              {/* type="button" */}
              {/* onClick={() => onJoinRoom('네오위즈홀딩스')} */}
              {/* > */}
              {/* 네오위즈홀딩스 <span className="dim">(1,293)</span> */}
              {/* </button> */}
              {/* </li> */}
              {/* <li> */}
              {/* <button */}
              {/* type="button" */}
              {/* onClick={() => */}
              {/* onJoinRoom( */}
              {/* JSON.stringify({ */}
              {/* name: '네이버', */}
              {/* type: 'Stock', */}
              {/* }), */}
              {/* ) */}
              {/* } */}
              {/* > */}
              {/* 네이버 <span className="dim">(1,081)</span> */}
              {/* </button> */}
              {/* </li> */}
              {/* <li> */}
              {/* <button type="button" onClick={() => onJoinRoom('삼성전자')}> */}
              {/* 삼성전자 <span className="dim">(781)</span> */}
              {/* </button> */}
              {/* </li> */}
              {/* <li> */}
              {/* <button type="button" onClick={() => onJoinRoom('시멘트 관련주')}> */}
              {/* 시멘트 관련주 <span className="dim">(661)</span> */}
              {/* </button> */}
              {/* </li> */}
              {/* <li> */}
              {/* <button type="button" onClick={() => onJoinRoom('통일 관련주')}> */}
              {/* 통일 관련주 <span className="dim">(620)</span> */}
              {/* </button> */}
              {/* </li> */}
              {/* <li> */}
              {/* <button type="button" onClick={() => onJoinRoom('통일 관련주')}> */}
              {/* 한국철강 <span className="dim">(491)</span> */}
              {/* </button> */}
              {/* </li> */}
            </ul>
          </Collapsible>
          <Collapsible
            open
            trigger={
              <div>
                <span>종목 채팅</span>
                <i className="fas fa-angle-down" />
              </div>
            }
            triggerWhenOpen={
              <div>
                <span>종목 채팅</span>
                <i className="fas fa-angle-up" />
              </div>
            }
          >
            <ul>
              {totalRooms
                .toList()
                .filter(e => e.type === 'Stock')
                .map(({ name, size }) => (
                  <li>
                    <button
                      type="button"
                      onClick={() =>
                        this.props.onJoinRoom(
                          JSON.stringify({
                            name,
                            type: 'Stock',
                          }),
                        )
                      }
                    >
                      {name} <span className="dim">({size})</span>
                    </button>
                  </li>
                ))}
            </ul>
          </Collapsible>
          <Collapsible
            open
            trigger={
              <div>
                <span>키워드 채팅</span>
                <i className="fas fa-angle-down" />
              </div>
            }
            triggerWhenOpen={
              <div>
                <span>키워드 채팅</span>
                <i className="fas fa-angle-up" />
              </div>
            }
          >
            <ul>
              {totalRooms
                .toList()
                .filter(e => e.type === 'StockKeyword')
                .map(({ name, size }) => (
                  <li>
                    <button
                      type="button"
                      onClick={() =>
                        this.props.onJoinRoom(
                          JSON.stringify({
                            name,
                            type: 'Stock',
                          }),
                        )
                      }
                    >
                      {name} <span className="dim">({size})</span>
                    </button>
                  </li>
                ))}
            </ul>
          </Collapsible>
        </div>
      </div>
    );
  }
}

ChatRoom.propTypes = {
  onJoinRoom: PropTypes.func,
  rooms: PropTypes.array,
};

export default ChatRoom;
