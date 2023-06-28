package com.techconnect.client.modules.splash.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techconnect.client.R
import com.techconnect.client.common.composables.CustomLinearProgressIndicator
import com.techconnect.client.common.composables.IndeterminateLinearRoundedProgressBar
import com.techconnect.client.common.getVersionName
import com.techconnect.client.modules.splash.screens.SplashScreen
import com.techconnect.client.ui.theme.TechConnectTheme
import com.techconnect.client.ui.theme.primaryColorTechConnect

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        //Logo image
        //App name
        //App version

        Image(
            painter = painterResource(id = R.drawable.tech_connect_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        IndeterminateLinearRoundedProgressBar(
            modifier =  Modifier.fillMaxWidth(0.7f),
            progressColor = primaryColorTechConnect
        )

        //App name
        Text(
            text = "Tech Connect",
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        //App version
        Text(
            text = getVersionName(LocalContext.current),
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenpreView() {
    val remnberNavController = NavController(LocalContext.current)
    TechConnectTheme {
        Splash()
    }
}