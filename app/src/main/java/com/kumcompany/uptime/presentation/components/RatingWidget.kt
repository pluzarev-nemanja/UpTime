package com.kumcompany.uptime.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kumcompany.uptime.R

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 3f,
    spaceBetween: Dp = 6.dp
) {
    val result = calculateHearts(rating = rating)
    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember {
        PathParser().parsePathString(heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        result["filledHearts"]?.let {
            repeat(it) {
                FilledHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["halfFilledHearts"]?.let {
            repeat(it) {
                HalfFilledHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
        result["emptyHearts"]?.let {
            repeat(it) {
                EmptyHeart(
                    heartPath = heartPath,
                    heartPathBounds = heartPathBounds,
                    scaleFactor = scaleFactor
                )
            }
        }
    }


}

@Composable
fun FilledHeart(
    heartPath: Path,
    heartPathBounds: Rect,
    scaleFactor: Float
) {

    Canvas(modifier = Modifier.size(24.dp)) {
        scale(scale = scaleFactor) {

            val canvasSize = size
            val pathWidth = heartPathBounds.width
            val pathHeight = heartPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)
            translate(
                left = left,
                top = top
            ) {
                drawPath(path = heartPath, color = Color.Red)

            }
        }
    }
}

@Composable
fun HalfFilledHeart(
    heartPath: Path,
    heartPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier
        .size(24.dp)
        .semantics {
            contentDescription = "HalfFilledHeart"
        }) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = heartPathBounds.width
            val pathHeight = heartPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)
            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = heartPath,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
                clipPath(path = heartPath) {
                    drawRect(
                        color = Color.Red,
                        size = Size(
                            width = heartPathBounds.maxDimension / 1.7f,
                            height = heartPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }

    }
}

@Composable
fun EmptyHeart(
    heartPath: Path,
    heartPathBounds: Rect,
    scaleFactor: Float
) {

    Canvas(modifier = Modifier
        .size(24.dp)
        .semantics {
            contentDescription = "EmptyHeart"
        }) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = heartPathBounds.width
            val pathHeight = heartPathBounds.height
            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)
            translate(
                left = left,
                top = top
            ) {
                drawPath(
                    path = heartPath,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            }
        }

    }
}


@Composable
fun calculateHearts(rating: Double): Map<String, Int> {
    val maxHearts by remember {
        mutableStateOf(5)
    }
    var filledHearts by remember {
        mutableStateOf(0)
    }
    var halfFilledHearts by remember {
        mutableStateOf(0)
    }
    var emptyHearts by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastNumber) = rating.toString().split(".").map {
            it.toInt()
        }
        if (firstNumber in 0..5 && lastNumber in 0..9) {
            filledHearts = firstNumber
            if (lastNumber in 1..5) {
                halfFilledHearts++
            }
            if (lastNumber in 6..9) {
                filledHearts++
            }
            if (firstNumber == 5 && lastNumber > 0) {
                emptyHearts = 5
                filledHearts = 0
                halfFilledHearts = 0
            }
        } else {
            Log.d("RatingWidget", "Invalid rating number.")
        }


    }
    emptyHearts = maxHearts - (filledHearts + halfFilledHearts)
    return mapOf(
        "filledHearts" to filledHearts,
        "halfFilledHearts" to halfFilledHearts,
        "emptyHearts" to emptyHearts
    )

}

@Preview(showBackground = true)
@Composable
fun HeartPrev() {
    val heartPathString = stringResource(id = R.string.heart_path)
    val heartPath = remember {
        PathParser().parsePathString(heartPathString).toPath()
    }
    val heartPathBounds = remember {
        heartPath.getBounds()
    }
    Row {
        FilledHeart(heartPath = heartPath, heartPathBounds = heartPathBounds, scaleFactor = 3f)
        HalfFilledHeart(heartPath = heartPath, heartPathBounds = heartPathBounds, scaleFactor = 3f)
        EmptyHeart(heartPath = heartPath, heartPathBounds = heartPathBounds, scaleFactor = 3f)
    }
}