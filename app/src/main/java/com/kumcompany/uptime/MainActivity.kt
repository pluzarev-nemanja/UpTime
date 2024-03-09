package com.kumcompany.uptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kumcompany.uptime.navigation.SetupNavGraph
import com.kumcompany.uptime.presentation.screens.main.MainScreen
import com.kumcompany.uptime.ui.theme.UpTimeTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpTimeTheme {

                navController = rememberNavController()

                MainScreen(
                    navController = navController
                )
            }
        }
    }
}
