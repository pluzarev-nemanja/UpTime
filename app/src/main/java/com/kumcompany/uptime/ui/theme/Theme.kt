package com.kumcompany.uptime.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Beige60,
    onPrimary = White90,
    onPrimaryContainer = White90,
    secondary = Beige70,
    onSecondary = White90,
    onSecondaryContainer = White90,
    primaryContainer = Beige70,
    secondaryContainer = Beige80,
    background = White80,
    surface = Beige90,
    onSurface = Beige70,
    onBackground = Beige80,
    inversePrimary = Beige90
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue30,
    onPrimary = White90,
    onPrimaryContainer = White90,
    secondary = DarkBlue20,
    onSecondary = DarkGrey40,
    onSecondaryContainer = White90,
    primaryContainer = DarkBlue40,
    secondaryContainer = DarkBlue30,
    background = DarkBlue10,
    surface = DarkBlue40,
    onSurface = White90,
    onBackground = DarkBlue30,
    inversePrimary = DarkBlue40
)

@Composable
fun UpTimeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}