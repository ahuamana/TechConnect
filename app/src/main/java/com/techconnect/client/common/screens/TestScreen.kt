package com.techconnect.client.common.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techconnect.client.common.composables.CustomLinearProgressIndicator
import com.techconnect.client.common.composables.IndeterminateLinearRoundedProgressBar

@Composable
fun TestScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Text(text = "Custom Linear Progress Indicator", fontSize = 30.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))
        Text(text = "Indeterminate Linear Rounded Progress Bar", fontSize = 20.sp)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        CustomLinearProgressIndicator(Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Text(text = "Indeterminate Linear Rounded Progress Bar with Canvas", fontSize = 20.sp)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        IndeterminateLinearRoundedProgressBar(modifier = Modifier.fillMaxWidth(), height = 15.dp)
    }
}

@Preview
@Composable
fun TestScreenPreview() {
    TestScreen()
}