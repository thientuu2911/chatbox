import { createAction, props } from '@ngrx/store';
import { Users } from 'src/app/model/users';

export const updateUsers = createAction('[User] Update Users', props<{ user: Users, currentUserId: number }>());
export const removeUser = createAction('[User] Remove User', props<{ id: number }>());