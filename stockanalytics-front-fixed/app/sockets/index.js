import io from 'socket.io-client';
import { fromJS, Map, List } from 'immutable/dist/immutable';
import * as types from '../containers/ChatSite/constants';
import {
  addUser,
  messageReceived,
  populateUsersList,
  updateRooms,
} from '../containers/ChatSite/actions';

const socketMiddleware = store => {
  const socket = io('http://localhost:8989');
  socket.emit('chat mounted');

  socket.on('new bc message', event => {
    const data = JSON.parse(event);
    switch (data.type) {
      case types.ADD_MESSAGE:
        store.dispatch(messageReceived(data.message, data.author));
        break;
      case types.ADD_USER:
        store.dispatch(addUser(data.name));
        break;
      case types.USERS_LIST:
        store.dispatch(populateUsersList(data.users));
        break;
      default:
        break;
    }
  });

  socket.on('update rooms', event => {
    const rooms = fromJS(Map(JSON.parse(event)))
      .filter(value => value !== null)
      .map((value, key) => ({
        name: key,
        type: value.type,
        size: value.size,
      }));

    console.log('rooms', rooms.toJS());
    store.dispatch(updateRooms(rooms));
  });

  return next => action => {
    switch (action.type) {
      case types.ADD_MESSAGE:
        socket.emit(
          'new message',
          JSON.stringify({
            ...action,
            author: action.author,
          }),
        );
        break;
      case types.JOIN_ROOM:
        socket.emit('join room', action.targetRoom);
        break;
      case types.LEAVE_ROOM:
        console.log('leave_room', action.currentRoom);
        socket.emit(
          'leave room',
          JSON.stringify({
            name: action.currentRoom,
          }),
        );
        break;
      default:
        break;
    }
    return next(action);
  };
};

export default socketMiddleware;
