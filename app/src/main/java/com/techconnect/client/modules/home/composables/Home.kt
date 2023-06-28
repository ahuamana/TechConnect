package com.techconnect.client.modules.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.techconnect.client.R
import com.techconnect.client.domain.UserGoogle

@Composable
fun Home(
    navController: NavController,
    userGoogle: UserGoogle? = null
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn {
                item {
                    //HomeHeader()
                }
                item {
                    //HomeBody()
                }
            }
        }
        LoadingScreen()
    }
}

@Composable
fun LoadingScreen() {
    val lottieLoading by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_amazing))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = lottieLoading,
                modifier = Modifier.
                fillMaxWidth(1f),
                iterations = LottieConstants.IterateForever)
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val navController = NavController(LocalContext.current)
    Home(navController = navController)
}