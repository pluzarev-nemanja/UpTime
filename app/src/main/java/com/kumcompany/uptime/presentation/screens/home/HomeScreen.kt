package com.kumcompany.uptime.presentation.screens.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allWatches = homeViewModel.getAllWatches.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar()
        }
    ) { paddingValues ->

    }
}