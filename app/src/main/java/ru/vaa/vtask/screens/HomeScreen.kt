package ru.vaa.vtask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vaa.vtask.components.CustomHeadingTextComponent
import ru.vaa.vtask.ui.theme.Monochrome10

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(Monochrome10)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CustomHeadingTextComponent(value = "Home")
        }
    }
}
