package com.jyoti.redux.redux

import android.util.Log


typealias Next<State> = (State, Action, Dispatch) -> Action
typealias Middleware<State> = (State, Action, Dispatch, Next<State>) -> Action

fun loggerMiddleware(state: AppState, action: Action, dispatch: Dispatch, next: Next<AppState>): Action {
    Log.d("middleware", "action in <-- $action")
    val newAction = next(state, action, dispatch)
    Log.d("middleware", "action out --> $newAction")
    return newAction
}