package com.example.postman.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
@Preview
@Composable
fun SplashScreen() {
    LocalContentColor
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


    LaunchedEffect(key1 = true) {
        delay(3000)
    }
}