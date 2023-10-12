import { ActionContext, ActionTree } from "vuex";
import { MutationType, Mutations } from "./mutations";
import { State } from "./state";

export enum ActionTypes {
    SetAuth = "SET_AUTH"
}

type ActionAugument = Omit<ActionContext<State, State>, "commit"> & {
    commit< K extends keyof Mutations>(
        key: K,
        payload: Parameters<Mutations[K]>[1]
    ): ReturnType<Mutations[K]>
}

export type Actions = {
    [ActionTypes.SetAuth](context: ActionAugument): void
}

export const actions: ActionTree<State, State> & Actions = {
    async [ActionTypes.SetAuth]({ commit }) {
        commit(MutationType.SetAuth, true)
    }
}