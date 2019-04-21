import React from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import injectReducer from 'utils/injectReducer';
import injectSaga from 'utils/injectSaga';
import { slide as Menu } from 'react-burger-menu';
import PropTypes from 'prop-types';
import { createStructuredSelector } from 'reselect';
import reducer from './modules';
import saga from './saga';
import ChatRoom from '../../components/Chat/ChatRoom';
import Chat from '../../components/Chat/Chat';
import { addMessage, joinRoom, leaveRoom } from './actions';
import {
  makeSelectMessages,
  makeSelectUsers,
  makeSelectRooms,
} from './selectors';
import MessagesList from '../../components/Chat/MessagesList';
import username from '../../utils/name';

class ChatSite extends React.PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      user: username(),
    };
  }

  joinRoom = targetRoom => {
    this.props.joinRoom(targetRoom);
  };

  leaveRoom = () => {
    this.props.leaveRoom(this.props.rooms.get('currentRoom'));
  };

  render() {
    const chatActivated = this.props.rooms.get('chatActivated');
    const currentRoom = this.props.rooms.get('currentRoom');

    return (
      <Menu
        // onStateChange={this.isMenuOpen}
        isOpen={chatActivated}
        width="375px"
        right
        burgerButtonClassName="far fa-comments chat"
        customBurgerIcon={<i className="far fa-comments" />}
      >
        {!chatActivated ? (
          <ChatRoom rooms={this.props.rooms} onJoinRoom={this.joinRoom} />
        ) : (
          <Chat
            user={this.state.user}
            currentRoom={currentRoom}
            messages={this.props.messages}
            addMessage={this.props.addMessage}
            leaveRoom={this.leaveRoom}
          />
        )}
        <MessagesList />
      </Menu>
    );
  }
}

ChatSite.propTypes = {
  addMessage: PropTypes.func,
  messages: PropTypes.object,
  joinRoom: PropTypes.func,
  leaveRoom: PropTypes.func,
  rooms: PropTypes.array,
};

export function mapDispatchToProps(dispatch) {
  return {
    addMessage: ({ message, author, currentRoom }) => {
      dispatch(addMessage(message, author, currentRoom));
    },
    joinRoom: targetRoom => {
      dispatch(joinRoom(targetRoom));
    },
    leaveRoom: currentRoom => {
      dispatch(leaveRoom(currentRoom));
    },
  };
}

const mapStateToProps = createStructuredSelector({
  rooms: makeSelectRooms,
  messages: makeSelectMessages,
  users: makeSelectUsers,
});

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'chat', reducer });
const withSaga = injectSaga({ key: 'chat', saga, test: 'test' });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(ChatSite);
