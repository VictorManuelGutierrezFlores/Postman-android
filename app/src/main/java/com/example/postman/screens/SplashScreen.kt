package com.example.postman.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavHostController
import com.example.postman.navigation.NavScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen( navController: NavHostController ) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.popBackStack()
        navController.navigate(NavScreen.WelcomeView.name)
    }

    val brush = remember {
        Brush.linearGradient(
            listOf(
                Color(0xFFE87AFF),
                Color(0xFFEFC640),
            )
        )
    }
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Bienvenido",
            style = MaterialTheme.typography.displayMedium,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.surfaceBright
        )
    }
}