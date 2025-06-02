package com.droidknights.app.core.designsystem.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier.size(40.dp),
    strokeWidth: Dp = 4.dp,
) {
    val color = KnightsTheme.colorScheme
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotationAngle",
    )

    Canvas(modifier = modifier) {
        val diameter = size.minDimension
        val topLeft = Offset(
            (size.width - diameter) / 2,
            (size.height - diameter) / 2,
        )

        drawArc(
            color = color.onSecondarySurface,
            startAngle = angle,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = topLeft,
            size = Size(diameter, diameter),
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
        )
    }
}
