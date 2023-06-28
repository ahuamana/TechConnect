package com.techconnect.client.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techconnect.client.ui.theme.TechConnectTheme

@Composable
fun CustomLinearProgressIndicator(modifier: Modifier, shape:RoundedCornerShape, colorIndicator : Color = Color(0xFF00BFA5)) {
    Card(
        modifier = modifier.background(
            color = Color.Transparent,
            shape = shape)) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            color = colorIndicator
        )
    }
}


@Preview
@Composable
fun CustomLinearProgressIndicatorPrev() {
    TechConnectTheme() {
        CustomLinearProgressIndicator(Modifier.fillMaxWidth(), RoundedCornerShape(20.dp))
    }
}