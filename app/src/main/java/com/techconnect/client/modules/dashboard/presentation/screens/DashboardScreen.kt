package com.techconnect.client.modules.dashboard.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.techconnect.client.common.composables.CustomErrorScreenSomethingHappens

@Composable
fun DashboardScreen(
    navController: NavController,
) {

    Column(modifier = Modifier.fillMaxSize()) {
        CustomErrorScreenSomethingHappens()
    }
}