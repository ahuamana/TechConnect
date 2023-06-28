package com.techconnect.client.modules.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techconnect.client.domain.UserData
import com.techconnect.client.modules.home.composables.Home
import com.techconnect.client.modules.home.composables.TopBarCustom
import com.techconnect.client.ui.theme.TechConnectTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    userData: UserData? = null
) {
    //val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBarCustom()
        },
        content = {
            Home(navController = navController, userData = userData)
        }
    )
}





@Preview
@Composable
fun HomeScreenPrev() {
    TechConnectTheme() {
        HomeScreen()
    }
}