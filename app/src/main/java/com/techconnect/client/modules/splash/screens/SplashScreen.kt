package com.techconnect.client.modules.splash.screens


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.techconnect.client.modules.splash.composables.Splash
import com.techconnect.client.navigation.AuthScreen
import com.techconnect.client.ui.theme.TechConnectTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun SplashScreen (navController: NavController){
    LaunchedEffect(key1 = true){
        delay(2000)
        yield()
        navController.popBackStack() // Remove splash screen from back stack
        navController.navigate(AuthScreen.Login.route)
    }

    Splash()
}


@Preview(showBackground = true)
@Composable
fun SplashScreenpreView() {
    val remnberNavController = NavController(LocalContext.current)
    TechConnectTheme {
        SplashScreen(remnberNavController)
    }
}
