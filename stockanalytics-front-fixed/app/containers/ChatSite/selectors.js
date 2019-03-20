import { createSelector } from 'reselect';

const selectChat = state => state.get('chat', []);

export const makeSelectMessages = createSelector(
  selectChat,
  chatState => chatState.messages,
);

export const makeSelectUsers = createSelector(
  selectChat,
  chatState => chatState.users,
);

export const makeSelectRooms = createSelector(
  selectChat,
  chatState => chatState.rooms,
)
