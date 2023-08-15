import { ActionReducerMap, MetaReducer } from "@ngrx/store";
import { hydrationMetaReducer } from "./hydration/hydration.reducer";
import { userReducers } from "./users/user.reducers";
import { UserState } from "./users/user.state";

export interface AppState{
    users: UserState;
}

export const appReducer: ActionReducerMap<AppState> = {
    users: userReducers
}
  
  export const metaReducers: MetaReducer[] = [
    hydrationMetaReducer
  ]