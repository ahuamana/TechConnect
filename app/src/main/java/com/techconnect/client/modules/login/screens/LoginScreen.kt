package com.techconnect.client.modules.login.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.techconnect.client.R
import com.techconnect.client.modules.login.presentation.SignInState
import com.techconnect.client.modules.login.presentation.LoginViewModel
import com.techconnect.client.ui.theme.TechConnectTheme

@Composable
fun LoginScreen(
    navController : NavController,
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val logoAnim by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.social_media_network))

    val lottieBackground by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.falling_stars_background))

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        if (state.signInError != null) {
            Toast.makeText(context, state.signInError, Toast.LENGTH_SHORT).show()
        }
    }


    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LottieAnimation(
                composition = lottieBackground,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                iterations = LottieConstants.IterateForever)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Box(modifier = Modifier.size(300.dp)){
                        //Lottieanimation automatically plays when it is created
                        LottieAnimation(
                            composition = logoAnim,
                            modifier = Modifier
                                .fillMaxSize(),
                            iterations = LottieConstants.IterateForever
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                //Title
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "Tech Connect",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily)
                }

                Spacer(modifier = Modifier.size(20.dp))

                //Login Button
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                    horizontalArrangement = Arrangement.Center) {
                    //Button
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .alpha(0.8f),
                        onClick = { onSignInClick() }) {
                        //content
                        //icon google svg
                        Image(painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google Icon",
                            modifier = Modifier.size(30.dp))
                        Text(
                            text ="INGRESAR",
                            modifier = Modifier.padding(start = 10.dp),
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily)
                    }
                }

            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    val navController = NavController(LocalContext.current)
    TechConnectTheme {
        LoginScreen(
            navController,
            SignInState(
                isSignInSuccessful = false,
                signInError = null
            ),
            onSignInClick = { },
        )
    }
}