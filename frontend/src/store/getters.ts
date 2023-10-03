import { GetterTree } from "vuex";
import { State } from "./state";

export type Getters = {
    authStatus(state: State): boolean
}

export const getters: GetterTree<State, State> & Getters = {
    authStatus(state) {
        return state.isAuthenticated
    }
}