import {
  ADD_MESSAGE,
  MESSAGE_RECEIVED,
  ADD_USER,
  USERS_LIST,
  JOIN_ROOM,
  LEAVE_ROOM,
  UPDATE_ROOMS,
} from './constants';

let nextMessageId = 0;
let nextUserId = 0;

export function addMessage(message, author, targetRoom) {
  nextMessageId += 1;
  return {
    type: ADD_MESSAGE,
    id: nextMessageId,
    message,
    author,
    targetRoom,
  };
}

export function addUser(name) {
  nextUserId += 1;
  return {
    type: ADD_USER,
    id: nextUserId,
    name,
  };
}

export function messageReceived(message, author) {
  nextMessageId += 1;
  return {
    type: MESSAGE_RECEIVED,
    id: nextMessageId,
    message,
    author,
  };
}

export function populateUsersList(users) {
  return {
    type: USERS_LIST,
    users,
  };
}

export function joinRoom(targetRoom) {
  return {
    type: JOIN_ROOM,
    targetRoom,
  };
}

export function leaveRoom(currentRoom) {
  return {
    type: LEAVE_ROOM,
    currentRoom,
  };
}

export function updateRooms(rooms) {
  return {
    type: UPDATE_ROOMS,
    rooms,
  };
}
