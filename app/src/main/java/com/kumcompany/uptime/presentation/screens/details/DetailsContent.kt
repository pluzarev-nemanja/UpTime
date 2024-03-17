package com.kumcompany.uptime.presentation.screens.details

import android.app.Activity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kumcompany.uptime.R
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.presentation.components.InfoBox
import com.kumcompany.uptime.util.Constants.BASE_URL

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedWatch: Watch?,
    colors: Map<String, String>
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    val currentSheetFraction = scaffoldState.currentSheetFraction
    val activity = LocalContext.current as Activity

    var vibrant by remember {
        mutableStateOf("#000000")
    }
    var darkVibrant by remember {
        mutableStateOf("#000000")
    }
    var onDarkVibrant by remember {
        mutableStateOf("#ffffff")
    }

    LaunchedEffect(key1 = selectedWatch) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!

    }
    SideEffect {
        activity.window.statusBarColor =
            Color(android.graphics.Color.parseColor(darkVibrant)).toArgb()
    }

    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f) 40.dp else 0.dp,
        label = ""
    )

    BottomSheetScaffold(
        sheetContent = {
            selectedWatch?.let {
                BottomSheetContent(
                    selectedWatch = it,
                    infoBoxIconColor = Color(android.graphics.Color.parseColor(onDarkVibrant)),
                    sheetBackgroundColor = Color(android.graphics.Color.parseColor(darkVibrant)),
                    contentColor = Color(android.graphics.Color.parseColor(onDarkVibrant))
                )
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 130.dp,
        sheetShape = RoundedCornerShape(topStart = radiusAnim, topEnd = radiusAnim),
    ) {
        selectedWatch?.let { watch ->
            BackgroundContent(
                watchImage = watch.image,
                imageFraction = currentSheetFraction,
                onCloseClicked = {
                    navController.popBackStack()
                },
                backGroundColor = Color(android.graphics.Color.parseColor(darkVibrant))
            )
        }
    }

}

@Composable
fun BackgroundContent(
    watchImage: String,
    imageFraction: Float,
    backGroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${watchImage}"


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundColor)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.placeholder)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + 0.6f)
                .align(Alignment.TopStart),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(10.dp),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close icon",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    selectedWatch: Watch,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.onBackground
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .weight(2f),
                painter = painterResource(id = R.drawable.time),
                contentDescription = "",
                tint = contentColor
            )
            Text(
                modifier = Modifier.weight(8f),
                text = selectedWatch.model,
                color = contentColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.casediameter),
                iconColor = infoBoxIconColor,
                bigText = "${selectedWatch.caseDiameter} mm",
                smallText = "Diameter",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.powerreserve),
                iconColor = infoBoxIconColor,
                bigText = "${selectedWatch.powerReserve} Hours",
                smallText = "Power",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.waterresistance),
                iconColor = infoBoxIconColor,
                bigText = selectedWatch.waterResistance,
                smallText = "Resistance",
                textColor = contentColor
            )

        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About",
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = contentColor,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 10.dp),
            text = selectedWatch.description,
            fontSize = MaterialTheme.typography.body1.fontSize,
            color = contentColor,
        )
        // TODO: Ovde ide kod za dodatne kartice


    }

}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }