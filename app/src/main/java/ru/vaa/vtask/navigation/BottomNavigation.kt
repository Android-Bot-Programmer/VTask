package ru.vaa.vtask.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.vaa.vtask.data.rules.NavigationItem
import ru.vaa.vtask.ui.theme.Monochrome50
import ru.vaa.vtask.ui.theme.Monochrome60
import ru.vaa.vtask.ui.theme.Primary
import ru.vaa.vtask.ui.theme.SystemBackground

@Composable
fun BottomNavigation(navController: NavHostController) {
    val navigationItemList = listOf(
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

    BottomNavigation(
        backgroundColor = SystemBackground,
        elevation = 0.dp
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        navigationItemList.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = item.icon,
                        contentDescription = item.description,
                        tint = if (currentRoute == item.route) Primary else Monochrome50
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = TextStyle(
                            color = if (currentRoute == item.route) Primary else Monochrome50,
                            fontSize = 14.sp
                        )
                    )
                },
                selectedContentColor = Primary,
                unselectedContentColor = Monochrome60
            )
        }
    }
}