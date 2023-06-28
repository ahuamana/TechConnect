package com.techconnect.client.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techconnect.client.domain.UserData
import com.techconnect.client.modules.dashboard.presentation.screens.DashboardScreen
import com.techconnect.client.modules.home.HomeScreen
import com.techconnect.client.modules.home.viewmodel.HomeViewModel


@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = GraphRoot.ROOT,
        startDestination = GraphRoot.AUTHENTICATION) {
        composableAuthNavGraph(navController = navController)
        composable(route = GraphRoot.HOME) {
            //Here home screen must have a unique navController to handle its own navigation
            val homeNavController = rememberNavController()
            //arguments
            LaunchedEffect(key1 = it) {
                val result = navController.currentBackStackEntry?.savedStateHandle?.get<UserData>("user")
                println("result userData : $result")
            }

            val viewModel = hiltViewModel<HomeViewModel>()
            val user = viewModel.user.value

            HomeScreen(
                navController = homeNavController,
                userData = user
            )
        }

        composable(
            route = GraphRoot.DASHBOARD
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val user = viewModel.user.value
            DashboardScreen(
                navController = navController,
            )
        }
    }
}

object GraphRoot {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DASHBOARD = "dashboard_graph"
    const val DETAILS = "details_graph"
}