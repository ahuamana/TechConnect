package com.techconnect.client.navigation

sealed class AppsScreens(val route:String){
    object SplashScreen : AppsScreens("splash_screen")
    object LoginScreen : AppsScreens("login_screen")
    object HomeScreen : AppsScreens("home_screen")
}
