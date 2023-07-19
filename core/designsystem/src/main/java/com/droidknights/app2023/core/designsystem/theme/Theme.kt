package com.droidknights.app2023.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Neon01,
    secondary = Green01,
    tertiary = Yellow01
)

private val LightColorScheme = lightColorScheme(
    primary = Neon01,
    secondary = Green01,
    tertiary = Yellow01
)

@Composable
fun KnightsTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        typography = Typography,
        content = content
    )
}
