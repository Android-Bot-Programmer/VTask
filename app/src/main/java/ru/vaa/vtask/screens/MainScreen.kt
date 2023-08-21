package ru.vaa.vtask.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.vaa.vtask.components.CustomFABHome
import ru.vaa.vtask.navigation.BottomNavigation
import ru.vaa.vtask.navigation.BottomNavigationGraph
import ru.vaa.vtask.ui.theme.SystemBackground

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = SystemBackground,
        floatingActionButton = {
            CustomFABHome(imageVector = Icons.Filled.Add) { }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        BottomNavigationGraph(navController = navController)
    }
}