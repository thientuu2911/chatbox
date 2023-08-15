import { createFeatureSelector, createSelector } from "@ngrx/store";
import { UserState } from "./user.state";

export const selectUserState = createFeatureSelector<UserState>('users');

export const selectUsers = createSelector(selectUserState, (state) => state.users);

export const selectCurrentUserId = createSelector(selectUserState, (state) => state.currentUserId);

export const selectCurrentUser = createSelector(
  selectUsers,
  selectCurrentUserId,
  (users, currentUserId) => users.find((user) => user.id === currentUserId) || null
);