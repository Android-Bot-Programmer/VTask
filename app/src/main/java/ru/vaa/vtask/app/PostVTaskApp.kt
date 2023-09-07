package ru.vaa.vtask.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.vaa.vtask.screens.AnimatedSplashScreen
import ru.vaa.vtask.navigation.PostVTaskRouter
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.screens.AccountScreen
import ru.vaa.vtask.screens.BoardScreen
import ru.vaa.vtask.screens.HomeScreen
import ru.vaa.vtask.screens.LogInScreen
import ru.vaa.vtask.screens.MainScreen
import ru.vaa.vtask.screens.SettingsScreen
import ru.vaa.vtask.screens.SignUpScreen
import ru.vaa.vtask.ui.theme.Monochrome10

@Composable
fun PostVTaskApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Monochrome10
    ) {
        Crossfade(targetState = PostVTaskRouter.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.SplashScreen -> {
                    AnimatedSplashScreen()
                }

                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.LogInScreen -> {
                    LogInScreen()
                }

                is Screen.BoardScreen -> {
                    BoardScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }

                is Screen.SettingsScreen -> {
                    SettingsScreen()
                }

                is Screen.MainScreen -> {
                    MainScreen()
                }

                is Screen.AccountScreen -> {
                    AccountScreen()
                }
            }
        }
    }
}