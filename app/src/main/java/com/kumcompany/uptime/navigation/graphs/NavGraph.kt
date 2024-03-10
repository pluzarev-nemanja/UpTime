package com.kumcompany.uptime.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kumcompany.uptime.navigation.routes.Graphs
import com.kumcompany.uptime.presentation.screens.main.MainScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = Graphs.ROOT.route
    ) {

        welcomeNavGraph(navController = navController)

        composable(Graphs.MAIN.route) {
            MainScreen()
        }

    }

}