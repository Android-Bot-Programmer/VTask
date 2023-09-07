package ru.vaa.vtask.data.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import ru.vaa.vtask.data.rules.NavigationItem

object Constants {
    val mainNavigationItemList = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            description = "Home Screen",
            route = "homeScreen"
        ),
        NavigationItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            description = "Settings Screen",
            route = "settingsScreen"
        )
    )
}