package com.jyoti.redux.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jyoti.redux.controller.RepoScreen

@Composable
fun GitHubNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = GitHubDestinations.LOGIN_ROUTE,
    navActions: GitHubNavigationActions = remember(navController) {
        GitHubNavigationActions(navController)
    }
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = GitHubDestinations.LOGIN_ROUTE
        ){
            LoginScreen(onLoginSuccess = {
                navActions.navigateToRepo()
            })
        }
        composable(route = GitHubDestinations.REPO_ROUTE){
            RepoScreen()
        }
    }

}