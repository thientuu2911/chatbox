import { Users } from "./users";

export interface Rooms{
    id: string;
    name: string;
    users: Users;
}