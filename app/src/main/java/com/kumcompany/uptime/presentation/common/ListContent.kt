package com.kumcompany.uptime.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kumcompany.uptime.R
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.navigation.routes.Screen
import com.kumcompany.uptime.presentation.components.RatingWidget
import com.kumcompany.uptime.util.Constants.BASE_URL

@Composable
fun ListContent(
    watches: LazyPagingItems<Watch>,
    navController: NavHostController,
    paddingValues: PaddingValues,
    bottomPaddingValues: PaddingValues
) {

    LazyColumn(
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 5.dp,
            start = 10.dp,
            end = 10.dp,
            bottom = bottomPaddingValues.calculateBottomPadding() + 5.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            count = watches.itemCount,
            key = watches.itemKey { it.id },
            contentType = watches.itemContentType { "contentType" }
        ) { index ->
            val item = watches[index]
            if (item != null) {
                WatchItem(watch = item, navController = navController)
            }
        }
    }
}

@Composable
fun WatchItem(
    watch: Watch,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(400.dp)
            .clickable {
                navController.navigate(Screen.Details.passWatchId(watchId = watch.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(
                size = 20.dp
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("$BASE_URL${watch.image}")
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(
                    0.4f
                )
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = watch.model,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Text(
                    text = watch.description,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(modifier = Modifier.padding(end = 4.dp), rating = watch.rating)
                    Text(
                        text = "(${watch.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}