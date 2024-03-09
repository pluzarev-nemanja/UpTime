package com.kumcompany.uptime.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{watchId}") {
        fun passWatchId(watchId: Int): String {
            return "details_screen/$watchId"
        }
    }

    object Search : Screen("search_screen")

}