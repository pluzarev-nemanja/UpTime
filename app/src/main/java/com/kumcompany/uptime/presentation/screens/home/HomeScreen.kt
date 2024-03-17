package com.kumcompany.uptime.presentation.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kumcompany.uptime.presentation.common.ListContent

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    bottomPaddingValues: PaddingValues
) {
    val allWatches = homeViewModel.getAllWatches.collectAsLazyPagingItems()
    val activity = LocalContext.current as Activity
    val systemBarColor = MaterialTheme.colorScheme.primary.toArgb()

    SideEffect {
        activity.window.statusBarColor = systemBarColor

    }

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