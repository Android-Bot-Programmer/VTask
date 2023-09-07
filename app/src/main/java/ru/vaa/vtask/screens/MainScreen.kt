package ru.vaa.vtask.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.vaa.vtask.components.CustomFABHome
import ru.vaa.vtask.data.utils.Constants
import ru.vaa.vtask.navigation.BottomNavigation
import ru.vaa.vtask.navigation.BottomNavigationGraph
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.ui.theme.SystemBackground

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = SystemBackground,
        floatingActionButton = {
            AnimatedVisibility(
                visible = currentRoute == Screen.HomeScreen.route,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                CustomFABHome(imageVector = Icons.Filled.Add) { }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomNavigation(
                navController = navController,
                navigationItemList = Constants.mainNavigationItemList,
                currentRoute = currentRoute
            )
        }
    ) {
        BottomNavigationGraph(navController = navController)
    }
}