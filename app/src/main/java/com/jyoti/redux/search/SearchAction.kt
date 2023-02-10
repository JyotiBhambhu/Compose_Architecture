package com.jyoti.redux.search

import com.jyoti.redux.redux.Action

object ClearSearch : Action
data class Search(val query: String) : Action
data class LoadingSearch(val loading: Boolean) : Action
data class SearchResultLoaded(val query: String, val data: List<String>) : Action
data class SearchFail(val errMessage: String) : Action