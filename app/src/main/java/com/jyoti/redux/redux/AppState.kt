package com.jyoti.redux.redux

import com.jyoti.redux.search.SearchState

data class AppState(val searchState: SearchState = SearchState())