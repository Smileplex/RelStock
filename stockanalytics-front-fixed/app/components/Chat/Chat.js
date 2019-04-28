import React from 'react';
import PropTypes from 'prop-types';

class Chat extends React.Component {
  componentDidMount() {
    this.chatTextArea.style.height = `${window.innerHeight - 210}px`;
    this.chatTextList.style.height = `${window.innerHeight - 230}px`;
  }

  scrollToBottom = () => {
    this.chatTextList.scrofllTop = this.chatTextList.scrollHeight;
  };

  componentDidUpdate() {
    this.scrollToBottom();
  }

  render() {
    const { messages, currentRoom, currentRoomSize } = this.props;
    return (
      <div className="chat">
        <div className="menu-header chat">
          <button type="button" onClick={() => this.props.leaveRoom()}>
            <i className="fas fa-angle-left" />
          </button>
          {currentRoom}
          <dim>&nbsp;{currentRoomSize}</dim>
        </div>
        <div className="content">
          <div className="chatBox">
            <div
              className="chatTextArea"
              ref={area => {
                this.chatTextArea = area;
              }}
            >
              <ul
                className="chatTextList"
                ref={area => {
                  this.chatTextList = area;
                }}
              >
                {messages.map(
                  item =>
                    item.id && (
                      <li
                        key={item.id}
                        className={
                          item.author === this.props.user ? 'self' : 'other'
                        }
                      >
                        <div className="head">
                          <span className="name">{item.author}</span>
                        </div>
                        <div className="body">
                          <div className="msg">{item.message}</div>
                          <span className="time">17:00</span>
                        </div>
                      </li>
                    ),
                )}
              </ul>
            </div>
            <div className="chatForm">
              <textarea
                ref={node => {
                  this.input = node;
                }}
                placeholder="메세지를 입력하세요"
                onKeyPress={e => {
                  if (e.key === 'Enter') {
                    this.props.addMessage({
                      message: this.input.value,
                      author: this.props.user,
                      currentRoom: this.props.currentRoom,
                    });
                    this.input.value = '';
                  }
                }}
              />
              <input
                onClick={() =>
                  this.props.addMessage({
                    message: this.input.value,
                    author: this.props.user,
                    currentRoom: this.props.currentRoom,
                  })
                }
                type="button"
                value="전송"
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Chat.propTypes = {
  user: PropTypes.object,
  addMessage: PropTypes.func,
  leaveRoom: PropTypes.func,
  messages: PropTypes.array,
  currentRoom: PropTypes.string,
  currentRoomSize: PropTypes.number,
};
export default Chat;
