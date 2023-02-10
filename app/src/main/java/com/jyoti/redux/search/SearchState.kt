package com.jyoti.redux.search

data class SearchState(
    val loading: Boolean = false,
    val query: String = "",
    val data: List<String> = listOf()
)
