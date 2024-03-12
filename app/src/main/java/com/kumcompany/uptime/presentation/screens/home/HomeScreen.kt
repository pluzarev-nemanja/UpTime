package com.kumcompany.uptime.presentation.screens.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {
            HomeTopBar()
        }
    ) { paddingValues ->

    }
}