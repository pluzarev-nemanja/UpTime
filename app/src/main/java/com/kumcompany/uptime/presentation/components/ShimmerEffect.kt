package com.kumcompany.uptime.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect(
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
        items(count = 2) {
            AnimatedShimmerItem()
        }
    }

}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition(label = "")
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing,

                ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(
    alpha: Float
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(size = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha)
                    .fillMaxWidth(0.5f)
                    .height(30.dp),
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(size = 16.dp)
            ) {

            }
            Spacer(modifier = Modifier.padding(10.dp))
            repeat(3) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha)
                        .fillMaxWidth()
                        .height(15.dp),
                    color = MaterialTheme.colorScheme.onSecondary,
                    shape = RoundedCornerShape(size = 16.dp)
                ) {
                }
                Spacer(modifier = Modifier.padding(6.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .size(20.dp),
                        color = MaterialTheme.colorScheme.onSecondary,
                        shape = RoundedCornerShape(size = 16.dp)
                    ) {
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}