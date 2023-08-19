package ru.vaa.vtask

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.vaa.vtask.navigation.Screen
import ru.vaa.vtask.navigation.VTaskRouter
import ru.vaa.vtask.ui.theme.Primary

@Composable
fun AnimatedSplashScreen() {
    val scale = remember {
        Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        VTaskRouter.navigateTo(Screen.BoardScreen)
        //navController.navigate(Screen1.StartBoard.route)
    }
    Splash(scale)
}

@Composable
fun Splash(scale: Animatable<Float, AnimationVector1D>) {
    // Image
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = R.string.app_name.toString(),
            modifier = Modifier
                .size(400.dp)
                .scale(scale.value)
        )
    }
}