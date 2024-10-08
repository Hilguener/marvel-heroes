package com.hilguener.marvelsuperheroes.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hilguener.marvelsuperheroes.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToMyApp: () -> Unit) {
    val scale =
        remember {
            Animatable(0f)
        }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec =
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
        )
        delay(3000L)
        onNavigateToMyApp()
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.marvel_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value),
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(onNavigateToMyApp = {})
}
