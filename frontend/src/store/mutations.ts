import { MutationTree } from "vuex";
import { State } from "./state";

export enum MutationType {
    SetAuth = "SET_AUTH"
}

export type Mutations = {
    [MutationType.SetAuth](state: State, isAuthenticated: boolean): void
}

export const mutations: MutationTree<State> & Mutations = {
    [MutationType.SetAuth](state, isAuthenticated){
        state.isAuthenticated = isAuthenticated
    }
}