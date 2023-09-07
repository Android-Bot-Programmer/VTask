package ru.vaa.vtask.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(
    val route: String = ""
) {
    object SplashScreen : Screen()
    object BoardScreen : Screen()
    object LogInScreen : Screen()
    object SignUpScreen : Screen()
    object AccountScreen : Screen(route = "accountScreen")
    object MainScreen : Screen(route = "mainScreen")
    object HomeScreen : Screen(route = "homeScreen")
    object SettingsScreen : Screen(route = "settingsScreen")
}

object PostVTaskRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SplashScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}