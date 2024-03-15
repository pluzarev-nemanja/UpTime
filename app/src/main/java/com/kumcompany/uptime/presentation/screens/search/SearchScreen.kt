package com.kumcompany.uptime.presentation.screens.search

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kumcompany.uptime.presentation.common.ListContent

@Composable
fun SearchScreen(
    bottomPaddingValues: PaddingValues,
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val watches = searchViewModel.searchedWatches.collectAsLazyPagingItems()


    val activity = LocalContext.current as Activity
    val systemBarColor = MaterialTheme.colorScheme.primary.toArgb()

    SideEffect { activity.window.statusBarColor = systemBarColor }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onSearchClicked = {
                    searchViewModel.searchWatches(query = it)
                },
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                }
            )
        },
        content = { paddingValues ->
            ListContent(
                watches = watches,
                navController = navController,
                paddingValues = paddingValues,
                bottomPaddingValues = bottomPaddingValues
            )

        }
    )
}