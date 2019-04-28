import { fromJS, Map, List } from 'immutable/dist/immutable';
import { JOIN_ROOM, UPDATE_ROOMS, LEAVE_ROOM } from '../constants';

export const initialState = fromJS({
  popularRooms: {},
  stockRooms: {},
  keywordRooms: {},
  chatActivated: false,
  currentRoom: '',
  currentRoomSize: '',
});

function rooms(state = initialState, action) {
  switch (action.type) {
    case UPDATE_ROOMS:
      return state
        .set(
          'stockRooms',
          action.rooms
            .filter(elem => elem.type === 'Stock')
            .sort((a, b) => b.size - a.size),
        )
        .set(
          'keywordRooms',
          action.rooms
            .filter(elem => elem.type === 'StockKeyword')
            .sort((a, b) => b.size - a.size),
        )
        .set(
          'popularRooms',
          action.rooms.sort((a, b) => b.size - a.size).slice(0, 3),
        )
        .set(
          'currentRoomSize',
          action.rooms
            .filter(elem => elem.name === state.get('currentRoom'))
            .map(elem => ({
              size: elem.size,
            }))
            .values()
            .next().value,
        );
    case JOIN_ROOM:
      return state
        .set('currentRoom', JSON.parse(action.targetRoom).name)
        .set('chatActivated', true);
    case LEAVE_ROOM:
      console.log('leaving target', action.currentRoom);
      return state.set('currentRoom', '').set('chatActivated', false);
    default:
      return state;
  }
}

export default rooms;
