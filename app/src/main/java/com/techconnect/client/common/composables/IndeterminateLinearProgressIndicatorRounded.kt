package com.techconnect.client.common.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateLinearRoundedProgressBar(
    modifier: Modifier,
    height: Dp = 20.dp,
    progressColor: Color = Color.Blue,
    backgroundColor: Color = Color.LightGray
) {
    val transition = rememberInfiniteTransition()
    val progressOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier
        .height(height = height)) {
        val cornerSize = CornerRadius(height.toPx())
        val barWidth = size.width * 0.6f
        val barHeight = size.height
        val startX = size.width * progressOffset
        val endX = startX + barWidth

        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(0f, 0f),
            size = Size(size.width, barHeight),
            cornerRadius = cornerSize
        )

        if (endX < size.width) {
            drawRoundRect(
                color = progressColor,
                topLeft = Offset(startX, 0f),
                size = Size(barWidth, barHeight),
                cornerRadius = cornerSize
            )
        } else {
            val remainingWidth = size.width - startX
            drawRoundRect(
                color = progressColor,
                topLeft = Offset(startX, 0f),
                size = Size(remainingWidth, barHeight),
                cornerRadius = cornerSize
            )
            drawRoundRect(
                color = progressColor,
                topLeft = Offset(0f, 0f),
                size = Size(barWidth - remainingWidth, barHeight),
                cornerRadius = cornerSize
            )
        }
    }
}

@Preview
@Composable
fun IndeterminateLinearRoundedProgressBarPrev() {
    IndeterminateLinearRoundedProgressBar(
        modifier = Modifier.fillMaxWidth(0.7f),
        height = 20.dp,
        progressColor = Color(0xFF00BFA5),
        backgroundColor = Color(0xFFE0E0E0)
    )
}