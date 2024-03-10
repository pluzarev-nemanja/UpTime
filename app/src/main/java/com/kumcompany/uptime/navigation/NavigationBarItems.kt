package com.kumcompany.uptime.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kumcompany.uptime.navigation.routes.Screen

sealed class NavigationBarItems(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationBarItems(
        route = Screen.Home.route,
        title = "HOME",
        icon = Icons.Filled.Home
    )

    object Search : NavigationBarItems(
        route = Screen.Search.route,
        title = "SEARCH",
        icon = Icons.Filled.Search
    )
}