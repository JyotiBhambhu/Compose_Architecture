package com.jyoti.redux.search

import com.jyoti.redux.redux.Action
import com.jyoti.redux.redux.AppState

fun reduceSearchState(state: AppState, action: Action): AppState {
    return when (action) {
        is ClearSearch -> state.copy(searchState = SearchState())
        is Search -> state.copy(searchState = SearchState(query = action.query))
        is LoadingSearch -> state.copy(searchState = SearchState(loading = true))
        is SearchResultLoaded -> state.copy(
            searchState = SearchState(
                loading = false,
                query = action.query,
                data = action.data
            )
        )
        is SearchError -> state.copy(searchState = SearchState(loading = false))
        else -> state
    }

}