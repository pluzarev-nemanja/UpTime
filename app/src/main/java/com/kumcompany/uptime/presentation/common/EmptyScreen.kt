package com.kumcompany.uptime.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.kumcompany.uptime.R
import com.kumcompany.uptime.domain.model.Watch
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    watches: LazyPagingItems<Watch>? = null
) {
    var message by remember {
        mutableStateOf("Find your favorite watch!")
    }
    var icon by remember {
        mutableStateOf(R.drawable.search_watch)
    }

    if (error != null) {
        message = parseErrorMessage(error)
        icon = R.drawable.server
    }
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        label = "",
        animationSpec = tween(durationMillis = 1000)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true

    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        watches = watches,
        error = error
    )


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    error: LoadState.Error? = null,
    watches: LazyPagingItems<Watch>? = null
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            watches?.refresh()
            isRefreshing = false
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state = refreshState, enabled = error != null),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        PullRefreshIndicator(
            state = refreshState,
            refreshing = isRefreshing
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon), contentDescription = "icon",
                tint = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary else Color.DarkGray,
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alpha = alphaAnim)
            )
            Text(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .alpha(alpha = alphaAnim),
                text = message,
                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary else Color.DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )

        }
    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }

        is ConnectException -> {
            "Internet Unavailable."
        }

        else -> {
            "Unknown error."
        }
    }
}