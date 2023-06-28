package com.techconnect.client.navigation

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.techconnect.client.modules.login.presentation.LoginViewModel
import com.techconnect.client.modules.login.screens.LoginScreen
import com.techconnect.client.modules.splash.screens.SplashScreen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

fun NavGraphBuilder.composableAuthNavGraph(
    navController: NavHostController
) {
    navigation(
        route = GraphRoot.AUTHENTICATION,
        startDestination = AuthScreen.SplashScreen.route
    ){
        composable(route = AuthScreen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = AuthScreen.Login.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val signInIntentSender by viewModel.signInIntentSender.collectAsStateWithLifecycle()

            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = {result ->
                    if(result.resultCode == RESULT_OK){
                        viewModel.signIn(
                            intent = result.data?:return@rememberLauncherForActivityResult)
                    }
                })

            LaunchedEffect(key1 = signInIntentSender){
                if(signInIntentSender != null){
                    launcher.launch(IntentSenderRequest.Builder(
                        signInIntentSender?:return@LaunchedEffect
                    ).build())
                }
            }

            LaunchedEffect(key1 = state.isSignInSuccessful){
                if(state.isSignInSuccessful){
                    state.isSignInSuccessful
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_SHORT).show()
                    //get user info
                    goToHome(navController = navController)
                }
            }

            LoginScreen(
                navController = navController,
                onSignInClick = {
                    println("onSignInClick")
                    viewModel.signInIntentSender()
                },
                state = state
            )
        }
    }
}

suspend fun goToHome(navController: NavController) = coroutineScope {
    navController.navigate(GraphRoot.DASHBOARD) {
        popUpTo(GraphRoot.AUTHENTICATION) {
            inclusive = true
        }
    }
}

sealed class AuthScreen(val route: String) {
    object SplashScreen : AuthScreen(route = "SPLASH")
    object Login : AuthScreen(route = "LOGIN")
}