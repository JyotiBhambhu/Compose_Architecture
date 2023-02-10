package com.jyoti.redux.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jyoti.redux.redux.AppState
import com.jyoti.redux.redux.AppStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(appState: AppState) {
    val query = appState.searchState.query
    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = {
                TextField(
                    value = query,
                    onValueChange = {  AppStore.instance.dispatch(Search(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )

        if(appState.searchState.loading){
            CircularProgressIndicator()
        }else{
            TextButton(
                onClick = {
                    AppStore.instance.dispatch(Search("query"))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Search")
            }
        }

        Text(
            text = "Search results for: $query",
            style = MaterialTheme.typography.headlineMedium
        )

        if(appState.searchState.data.isNotEmpty()){
            TextButton(
                onClick = {
                    AppStore.instance.dispatch(ClearSearch)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Clear Search")
            }
        }

    }
}
