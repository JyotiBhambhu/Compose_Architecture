package com.jyoti.redux.search

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.jyoti.redux.redux.Action
import com.jyoti.redux.redux.AppState
import com.jyoti.redux.redux.Dispatch
import com.jyoti.redux.redux.Next
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun searchMiddleware(state: AppState, action: Action, dispatch: Dispatch, next: Next<AppState>): Action {
    return when(action) {
        is Search -> {
            search(action.query, dispatch)
            LoadingSearch(true)
        }
        else -> action
    }
}

private fun search(
    query: String, dispatch: Dispatch
) {
    val httpClient = HttpClient {
        defaultRequest {
            header("Connection", "close")
            header("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            Gson()
        }
        install(HttpCache)
        install(HttpTimeout) {
            requestTimeoutMillis = 30_000L
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                }
            }
            level = LogLevel.INFO
        }
    }
    val url: String =
        "https://api.nasa.gov/planetary/apod?" +
                "api_key=l1Gq4scQZ6HjE17FT77oGIQWYNcOVZ99PmOQo5st" +
                "&thumbs=true"
    GlobalScope.launch {
        val body: String = httpClient.get(url).body()
        val res =
            Gson().fromJson(body, ApodModel::class.java).title
        withContext(Dispatchers.Main){
            dispatch(SearchResultLoaded(query = query, listOf(res)))
        }
    }

}

data class ApodModel(
    @SerializedName("title")
    val title: String = "",
)