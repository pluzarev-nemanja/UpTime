package com.kumcompany.uptime.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationBarItems(val icon:ImageVector) {
    Home(icon = Icons.Filled.Home),
    Search(icon = Icons.Filled.Search)
}