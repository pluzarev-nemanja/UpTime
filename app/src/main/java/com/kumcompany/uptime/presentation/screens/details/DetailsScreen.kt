package com.kumcompany.uptime.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kumcompany.uptime.util.Constants.BASE_URL
import com.kumcompany.uptime.util.PaletteGenerator.convertImageUrlToBitmap
import com.kumcompany.uptime.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val selectedWatch by detailsViewModel.selectedWatch.collectAsState()
    val colorPalette by detailsViewModel.colorPalette


    if(colorPalette.isNotEmpty()){
        DetailsContent(
            navController = navController,
            selectedWatch = selectedWatch,
            colors = colorPalette
        )
    }else{
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedWatch?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(
                                bitmap
                            )
                        )
                    }
                }
            }
        }
    }
}