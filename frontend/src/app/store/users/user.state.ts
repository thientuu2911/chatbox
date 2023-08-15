import { Users } from "src/app/model/users";

export interface UserState{
    users: Users[];
    currentUserId: number | null;
}