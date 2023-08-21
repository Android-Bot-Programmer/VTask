package ru.vaa.vtask.data.rules

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val description: String,
    val route: String,
    val icon: ImageVector
)