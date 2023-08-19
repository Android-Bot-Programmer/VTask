package ru.vaa.vtask.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.vaa.vtask.AnimatedSplashScreen
import ru.vaa.vtask.StartBoard
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.VTaskRouter
import ru.vaa.vtask.screens.LogInScreen
import ru.vaa.vtask.screens.SignUpScreen
import ru.vaa.vtask.ui.theme.Monochrome10

@Composable
fun VTaskApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Monochrome10
    ) {
        Crossfade(targetState = VTaskRouter.currentScreen, label = "") { currentState ->
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
                    StartBoard()
                }

                else -> {}
            }
        }
    }
}