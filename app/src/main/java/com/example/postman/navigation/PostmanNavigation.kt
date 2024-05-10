package com.example.postman.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.postman.screens.AddRegisters
import com.example.postman.screens.Dashboard
import com.example.postman.screens.DeleteRegisters
import com.example.postman.screens.GetRegisters
import com.example.postman.screens.LoginView
import com.example.postman.screens.SignUpView
import com.example.postman.screens.SplashScreen
import com.example.postman.screens.UpdateRegisters
import com.example.postman.screens.WelcomeView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostmanNavigation( navController: NavHostController ){
    NavHost(navController = navController,
        startDestination = NavScreen.WelcomeView.name) {
        composable(NavScreen.Dashboard.name){
            Dashboard()
        }
        composable(NavScreen.AddRegisters.name){
            AddRegisters()
        }
        composable(NavScreen.UpdateRegisters.name){
            UpdateRegisters()
        }
        composable(NavScreen.DeleteRegisters.name){
            DeleteRegisters()
        }
        composable(NavScreen.GetRegisters.name){
            GetRegisters()
        }
        composable(NavScreen.SplashScreen.name){
            SplashScreen()
        }
        composable(NavScreen.WelcomeView.name){
            WelcomeView()
        }
        composable(NavScreen.LoginScreen.name){
            LoginView()
        }
        composable(NavScreen.SignUpScreen.name){
            SignUpView()
        }
    }
}