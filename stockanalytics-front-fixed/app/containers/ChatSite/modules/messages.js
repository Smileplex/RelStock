import { fromJS } from 'immutable/dist/immutable';
import { ADD_MESSAGE, MESSAGE_RECEIVED, JOIN_ROOM, LEAVE_ROOM } from '../constants';

export const initialState = fromJS([
  {
    message: '',
    author: '',
    id: '',
  },
]);

function messages(state = initialState, action) {
  switch (action.type) {
    case ADD_MESSAGE:
    case MESSAGE_RECEIVED:
      return state.push({
        message: action.message,
        author: action.author,
        id: action.id,
      });
    case JOIN_ROOM:
      return state.clear();
    case LEAVE_ROOM:
      return state.clear();
    default:
      return state;
  }
}

export default messages;
