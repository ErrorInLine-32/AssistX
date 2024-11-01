package com.example.assistx.navigation

import HomeScreen
import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assistx.screens.Login.LoginScreen
import com.example.assistx.screens.Signup.SignUpScreen
import PreSplashScreen

@Composable
fun AssistXNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AssistX_Screens.PreSplashScreen.name) {
        composable(AssistX_Screens.PreSplashScreen.name) {
            PreSplashScreen(navController = navController)
        }
        composable(AssistX_Screens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable(AssistX_Screens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(AssistX_Screens.SignUpScreen.name) {
            SignUpScreen(navController = navController)
        }
        composable(AssistX_Screens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
    }
}