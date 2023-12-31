package ru.vaa.vtask.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.vaa.vtask.data.rules.NavigationItem
import ru.vaa.vtask.ui.theme.Monochrome50
import ru.vaa.vtask.ui.theme.Monochrome60
import ru.vaa.vtask.ui.theme.Primary
import ru.vaa.vtask.ui.theme.SystemBackground

@Composable
fun BottomNavigation(
    navController: NavHostController,
    navigationItemList: List<NavigationItem>,
    currentRoute: String?
) {
    BottomNavigation(
        backgroundColor = SystemBackground,
        elevation = 0.dp
    ) {
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