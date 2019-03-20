import { Map } from 'immutable/dist/immutable';
import { ADD_USER, USERS_LIST } from '../constants';

export const initialState = Map({});

function users(state = initialState, action) {
  switch (action.type) {
    case ADD_USER:
      return state.set([
        {
          name: action.name,
          id: action.id,
        },
      ]);
    case USERS_LIST:
      return action.users;
    default:
      return state;
  }
}

export default users;
