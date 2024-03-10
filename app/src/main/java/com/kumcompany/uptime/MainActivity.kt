package com.kumcompany.uptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kumcompany.uptime.domain.use_cases.UseCases
import com.kumcompany.uptime.navigation.routes.Graphs
import com.kumcompany.uptime.navigation.graphs.RootNavGraph
import com.kumcompany.uptime.ui.theme.UpTimeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @Inject
    lateinit var useCases: UseCases

    private var completed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            UpTimeTheme {
                Surface {

                    navController = rememberNavController()

                    RootNavGraph(
                        navController = navController,
                        startDestination = if (completed) Graphs.MAIN.route else Graphs.WELCOME.route
                    )

                }

            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            useCases.readOnBoardingUseCase().collect {
                completed = it
            }
        }

    }
}
