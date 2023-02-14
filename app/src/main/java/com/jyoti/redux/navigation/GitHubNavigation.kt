package com.jyoti.redux.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.jyoti.redux.navigation.GitHubScreens.LOGIN_SCREEN
import com.jyoti.redux.navigation.GitHubScreens.REPO_SCREEN
import com.jyoti.redux.navigation.GitHubScreens.SEARCH_SCREEN

/**
 * Screens used in [GitHubDestinations]
 */
private object GitHubScreens {
    const val REPO_SCREEN = "repo"
    const val LOGIN_SCREEN = "login"
    const val SEARCH_SCREEN = "search"
}

/**
 * Destinations used in the [MainActivity]
 */
object GitHubDestinations {
    const val REPO_ROUTE = REPO_SCREEN
    const val SEARCH_ROUTE = SEARCH_SCREEN
    const val LOGIN_ROUTE = LOGIN_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class GitHubNavigationActions(private val navController: NavHostController) {

    fun navigateToRepo() {
        navController.navigate(GitHubDestinations.REPO_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun navigateToSearch() {
        navController.navigate(GitHubDestinations.SEARCH_ROUTE) {
            navController.popBackStack()
        }
    }

    fun navigateToLogin() {
        navController.navigate(GitHubDestinations.LOGIN_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
        }
    }
}