package com.kumcompany.uptime.presentation.screens.main

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.kumcompany.uptime.navigation.NavigationBarItems
import com.kumcompany.uptime.navigation.graphs.HomeNavGraph

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {

    val navigationBarItems = listOf(
        NavigationBarItems.Home,
        NavigationBarItems.Search
    )

    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = navigationBarItems.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (bottomBarDestination) {
                AnimatedNavigationBar(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(64.dp),
                    selectedIndex = selectedIndex,
                    cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
                    ballAnimation = Parabolic(tween(300)),
                    indentAnimation = Height(tween(300)),
                    barColor = MaterialTheme.colorScheme.primary,
                    ballColor = MaterialTheme.colorScheme.primary
                ) {
                    navigationBarItems.forEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .noRippleClickable {
                                    selectedIndex = index
                                    navController.navigate(item.route)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(26.dp),
                                imageVector = item.icon,
                                contentDescription = "Bottom bar icon",
                                tint = if (selectedIndex == index) MaterialTheme.colorScheme.onPrimary
                                else MaterialTheme.colorScheme.inversePrimary
                            )
                        }
                    }

                }
            }
        }
    ) { paddingValues ->
        HomeNavGraph(navController = navController,paddingValues)
    }
}