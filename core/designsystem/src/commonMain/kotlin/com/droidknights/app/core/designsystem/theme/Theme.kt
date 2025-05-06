package com.droidknights.app.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

// Light/Dark 모두 컴포넌트 + 화면 추가에 따라 색상 추가해야함
private val DarkColorScheme = darkColorScheme(
    primary = Blue02,
    onPrimary = White,
    background = Black,
    surface = Graphite,
    onSurface = White,
    onSurfaceVariant = DarkGray,
    surfaceContainer = Graphite,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue01,
    onPrimary = White,
    background = PaleGray,
    surface = White,
    onSurface = Black,
    onSurfaceVariant = LightGray,
    surfaceContainer = White,
)

val LocalDarkTheme = compositionLocalOf { true }

@Composable
fun KnightsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalTypography provides Typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object KnightsTheme {
    val typography: KnightsTypography
        @Composable
        get() = LocalTypography.current
}
