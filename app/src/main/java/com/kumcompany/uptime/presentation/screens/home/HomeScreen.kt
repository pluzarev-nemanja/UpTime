package com.kumcompany.uptime.presentation.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.kumcompany.uptime.presentation.common.ListContent

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController : NavHostController,
    bottomPaddingValues: PaddingValues
) {
    val allWatches = homeViewModel.getAllWatches.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar()
        }
    ) { paddingValues ->
        ListContent(
            watches = allWatches,
            navController = navController,
            paddingValues = paddingValues,
            bottomPaddingValues = bottomPaddingValues
        )
    }
}