import { combineReducers } from 'redux';
import messages from './messages';
import users from './users';
import rooms from './rooms';

const chat = combineReducers({
  messages,
  users,
  rooms,
});

export default chat;
