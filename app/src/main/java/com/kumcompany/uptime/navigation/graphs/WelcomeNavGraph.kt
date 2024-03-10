package com.kumcompany.uptime.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kumcompany.uptime.navigation.routes.Graphs
import com.kumcompany.uptime.navigation.routes.Screen
import com.kumcompany.uptime.presentation.screens.welcome.WelcomeScreen

fun NavGraphBuilder.welcomeNavGraph(navController: NavHostController) {
    navigation(
        route = Graphs.WELCOME.route,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route){
            WelcomeScreen(
                navController = navController
            )
        }
    }
}