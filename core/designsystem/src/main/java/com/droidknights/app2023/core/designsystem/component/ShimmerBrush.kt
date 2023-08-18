package com.droidknights.app2023.core.designsystem.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun shimmerBrush(
    enabled: Boolean = true,
    targetValue: Float = 2000f,
    animationDuration: Int = 500,
    color: Color = MaterialTheme.colorScheme.outline
): Brush {
    return if (enabled) {
        val shimmerColors = listOf(
            color.copy(alpha = 0.6f),
            color.copy(alpha = 0.0f),
            color.copy(alpha = 0.6f),
        )
        val transition = rememberInfiniteTransition(label = "infiniteTransition")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(animationDuration),
                repeatMode = RepeatMode.Reverse
            ),
            label = "translateAnimation"
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(
                x = translateAnimation.value,
                y = translateAnimation.value
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}
