import { fromJS, Map, List } from 'immutable/dist/immutable';
import { UPDATE_ROOMS } from '../constants';

export const initialState = fromJS({
  popularRooms: {},
  stockRooms: {},
  keywordRooms: {},
});

function rooms(state = initialState, action) {
  switch (action.type) {
    case UPDATE_ROOMS:
      return state.set(
        'stockRooms',
        action.rooms.filter(elem => elem !== null),
      );
    default:
      return state;
  }
}

export default rooms;
