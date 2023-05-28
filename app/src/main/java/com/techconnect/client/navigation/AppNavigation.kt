package com.techconnect.client.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techconnect.client.modules.login.screens.LoginScreen
import com.techconnect.client.modules.splash.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppsScreens.SplashScreen.route) {
        composable(route = AppsScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = AppsScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = AppsScreens.HomeScreen.route) {
            //MainActivity(): TODO: Create HomeScreen
        }
    }
}