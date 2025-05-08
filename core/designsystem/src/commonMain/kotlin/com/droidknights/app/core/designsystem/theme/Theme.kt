package com.droidknights.app.core.designsystem.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val LocalDarkTheme = compositionLocalOf { true }

@Composable
fun KnightsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalColorScheme provides if (darkTheme) {
            KnightsColorScheme.darkColorScheme
        } else {
            KnightsColorScheme.lightColorScheme
        },
        LocalIndication provides ripple(),
        LocalTypography provides Typography,
        content = content
    )
}

object KnightsTheme {

    val colorScheme: KnightsColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: KnightsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
