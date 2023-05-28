package com.techconnect.client.modules.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.techconnect.client.R
import com.techconnect.client.modules.Greeting
import com.techconnect.client.ui.theme.TechConnectTheme

@Composable
fun LoginScreen() {
    val logoAnim by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.social_media_network))

    val lottieBackground by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.falling_stars_background))

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
                        modifier = Modifier.fillMaxWidth().height(50.dp).alpha(0.8f),
                        onClick = { /*TODO*/ }) {
                        //content
                        //icon google svg
                        Image(painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google Icon",
                            modifier = Modifier.size(30.dp))
                        Text(
                            text ="INGRESAR",
                            modifier = Modifier.padding(start = 10.dp),
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
    TechConnectTheme {
        LoginScreen()
    }
}