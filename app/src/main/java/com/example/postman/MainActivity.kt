package com.example.postman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.postman.screens.components.BottomNavigation
import com.example.postman.navigation.PostmanNavigation
import com.example.postman.screens.DeleteRegistersScreen
import com.example.postman.screens.SignUpView
import com.example.postman.screens.WelcomeView
import com.example.postman.ui.theme.PostmanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostmanTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomNavigation(navController) }
    ){paddingValues -> 
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()){
            PostmanNavigation(navController = navController)
        }
    }
}