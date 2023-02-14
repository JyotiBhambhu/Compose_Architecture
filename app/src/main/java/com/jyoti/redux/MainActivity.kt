package com.jyoti.redux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jyoti.redux.navigation.GitHubNavGraph
import com.jyoti.redux.redux.AppState
import com.jyoti.redux.redux.AppStore
import com.jyoti.redux.redux.Dispatch
import com.jyoti.redux.redux.Unsubscribe
import com.jyoti.redux.ui.theme.ReduxAppTheme

class MainActivity : ComponentActivity() {

    private var appState: AppState by mutableStateOf(AppState())
    private var dispatch: Dispatch? = null
    private lateinit var unsubscribe: Unsubscribe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReduxAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GitHubNavGraph(appState, dispatch)
                }
            }
        }
        AppStore.instance.subscribe{ appState, dispatch ->
            this.appState = appState
            this.dispatch = dispatch
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe.invoke()
        dispatch = null
    }

}
