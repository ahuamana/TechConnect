package com.techconnect.client.modules.splash.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techconnect.client.R
import com.techconnect.client.common.getVersionName
import com.techconnect.client.modules.splash.composables.Splash
import com.techconnect.client.navigation.AppsScreens
import com.techconnect.client.ui.theme.TechConnectTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun SplashScreen (navController: NavController){
    LaunchedEffect(key1 = true){
        delay(2000)
        yield()
        navController.popBackStack() // Remove splash screen from back stack
        navController.navigate(AppsScreens.LoginScreen.route)
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
