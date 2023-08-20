package ru.vaa.vtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.vaa.vtask.app.PostVTaskApp
import ru.vaa.vtask.ui.theme.VTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VTaskTheme {
                PostVTaskApp()
            }
        }
    }
}