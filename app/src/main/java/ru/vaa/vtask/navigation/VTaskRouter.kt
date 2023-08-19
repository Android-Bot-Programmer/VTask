package ru.vaa.vtask.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    object SplashScreen : Screen()
    object BoardScreen : Screen()
    object LogInScreen : Screen()
    object SignUpScreen : Screen()
    object HomeScreen : Screen()
}

object VTaskRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SplashScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}