import { createReducer, on } from '@ngrx/store';
import { updateUsers, removeUser } from './user.actions';
import { UserState } from './user.state';
export const initialState: UserState = {
  users: [],
  currentUserId: null,
};

export const userReducers = createReducer(
  initialState,
  on(updateUsers, (state, { user, currentUserId }) => {
    const isUserInList = state.users.find(existingUser => existingUser.id === user.id);
    if (isUserInList) {
      const updatedUsers = state.users.map(u => {
        if (user.id === u.id) {
          return user;
        }
        return u;
      });
      return {...state, users: updatedUsers };
    } else {
      return {
        ...state,
        users: [...state.users, user],
        currentUserId: currentUserId
      };
    }
  }),
  on(removeUser, (state, { id }) => ({
    ...state,
    users: state.users.filter((user) => user.id !== id),
  }))
);