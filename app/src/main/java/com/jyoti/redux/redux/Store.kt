package com.jyoti.redux.redux

import com.jyoti.redux.search.reduceSearchState

typealias Reducer<State> = (State, Action) -> State
typealias Subscription<State> = (State) -> Unit //StateChangeListener

interface Store<State> {
    fun getState(): State
    fun dispatch(action: Action)
    fun subscribe(subscription: Subscription<State>)
    fun unsubscribe(subscription: Subscription<State>)
}

abstract class SimpleStore<State>(
    initialState: State,
    private val reducers: List<Reducer<State>>
) : Store<State> {

    private var currentState: State = initialState
    private val subscriptions = arrayListOf<Subscription<State>>()

    override fun getState() = currentState

    override fun dispatch(action: Action) {
        val newState = applyReducers(currentState, action)
        if (currentState == newState) {
            //No change
            return
        }
        currentState = newState
        subscriptions.forEach { it(currentState) }
    }

    private fun applyReducers(current: State, action: Action): State {
        var newState = current
        for (reducer in reducers) {
            newState = reducer(newState, action)
        }
        return newState
    }

    override fun subscribe(subscription: Subscription<State>) {
        subscriptions.add(subscription)
        subscription.invoke(currentState)
    }

    override fun unsubscribe(subscription: Subscription<State>) {
        subscriptions.remove(subscription)
    }

}

class AppStore: SimpleStore<AppState>(
    initialState = AppState(),
    reducers = listOf(::reduceSearchState)) {

    companion object {
        val instance by lazy {
            AppStore()
        }
    }

}