package com.kumcompany.uptime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kumcompany.uptime.presentation.screens.home.HomeScreen
import com.kumcompany.uptime.presentation.screens.welcome.WelcomeScreen
import com.kumcompany.uptime.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {

    NavHost(navController = navController, startDestination = startDestination){

        composable(Screen.Welcome.route){
            WelcomeScreen(
                navController = navController
            )
        }
        composable(Screen.Home.route){
            HomeScreen()
        }
        composable(Screen.Details.route,
            arguments = listOf(
                navArgument(
                    DETAILS_ARGUMENT_KEY
                ){
                    type = NavType.IntType
                }
            )
        ){

        }
        composable(Screen.Search.route){

        }
    }

}