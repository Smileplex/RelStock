import React from 'react';
import Collapsible from 'react-collapsible';
import PropTypes from 'prop-types';

const ChatRoom = ({ onJoinRoom, rooms }) => {
  const stockRooms = rooms.get('stockRooms');
  console.log('stockRooms', stockRooms.toList().toJS());
  // noinspection JSAnnotator
  return (
    <div className="chat-room">
      <div className="menu-header">
        채팅 <dim>(8,255)</dim>
      </div>
      <div className="content">
        <div className="search-bar">
          <i className="icon fa fa-search" />
          <input
            className="input-text"
            type="text"
            name="eingabe"
            placeholder="종목명 또는 키워드를 검색하세요"
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
            {stockRooms.toList().map(({ name, count }) => (
              <li>
                <button
                  type="button"
                  onClick={() => onJoinRoom('네오위즈홀딩스')}
                >
                  {name} <span className="dim">({count})</span>
                </button>
              </li>
            ))}
            <li>
              <button
                type="button"
                onClick={() => onJoinRoom('네오위즈홀딩스')}
              >
                네오위즈홀딩스 <span className="dim">(1,293)</span>
              </button>
            </li>
            <li>
              <button type="button" onClick={() => onJoinRoom('네이버')}>
                네이버 <span className="dim">(1,081)</span>
              </button>
            </li>
            <li>
              <button type="button" onClick={() => onJoinRoom('삼성전자')}>
                삼성전자 <span className="dim">(781)</span>
              </button>
            </li>
            <li>
              <button type="button" onClick={() => onJoinRoom('시멘트 관련주')}>
                시멘트 관련주 <span className="dim">(661)</span>
              </button>
            </li>
            <li>
              <button type="button" onClick={() => onJoinRoom('통일 관련주')}>
                통일 관련주 <span className="dim">(620)</span>
              </button>
            </li>
            <li>
              <button type="button" onClick={() => onJoinRoom('통일 관련주')}>
                한국철강 <span className="dim">(491)</span>
              </button>
            </li>
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
            <li>
              종목이름 <span className="dim">(접속자수)</span>
            </li>
            <li>
              종목이름 <span className="dim">(접속자수)</span>
            </li>
            <li>
              종목이름 <span className="dim">(접속자수)</span>
            </li>
            <li>
              종목이름 <span className="dim">(접속자수)</span>
            </li>
            <li>
              종목이름 <span className="dim">(접속자수)</span>
            </li>
            <li>
              종목이름 <span className="dim">(접속자수)</span>
            </li>
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
            <li>
              키워드이름 <span className="dim">(1,293)</span>
            </li>
            <li>
              키워드이름 <span className="dim">(1,081)</span>
            </li>
            <li>
              키워드이름 <span className="dim">(723)</span>
            </li>
            <li>
              키워드이름 <span className="dim">(661)</span>
            </li>
            <li>
              키워드이름 <span className="dim">(620)</span>
            </li>
            <li>
              키워드이름 <span className="dim">(491)</span>
            </li>
          </ul>
        </Collapsible>
      </div>
    </div>
  );
};

ChatRoom.propTypes = {
  onJoinRoom: PropTypes.func,
  rooms: PropTypes.array,
};
export default ChatRoom;
