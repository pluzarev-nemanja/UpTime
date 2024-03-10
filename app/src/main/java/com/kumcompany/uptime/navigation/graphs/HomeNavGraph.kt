package com.kumcompany.uptime.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kumcompany.uptime.navigation.routes.Graphs
import com.kumcompany.uptime.navigation.routes.Screen
import com.kumcompany.uptime.presentation.screens.home.HomeScreen
import com.kumcompany.uptime.presentation.screens.search.SearchScreen
import com.kumcompany.uptime.util.Constants

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graphs.MAIN.route,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route){
            HomeScreen()
        }

        composable(
            Screen.Details.route,
            arguments = listOf(
                navArgument(
                    Constants.DETAILS_ARGUMENT_KEY
                ) {
                    type = NavType.IntType
                }
            )
        ) {

        }

        composable(Screen.Search.route) {
            SearchScreen()
        }
    }
}