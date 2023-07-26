package com.droidknights.app2023.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Neon01,
    primaryContainer = Graphite,
    onPrimaryContainer = White,
    inversePrimary = Green03,
    secondary = Green04,
    onSecondary = Green01,
    secondaryContainer = Green04,
    onSecondaryContainer = White,
    tertiary = Yellow05,
    onTertiary = Yellow01,
    tertiaryContainer = Yellow04,
    onTertiaryContainer = White,
    error = Red02,
    onError = Red05,
    errorContainer = Red04,
    onErrorContainer = Red01,
    surface = Graphite,
    onSurface = White,
    inverseSurface = Neon05,
    inverseOnSurface = Black,
    outline = DarkGray,
    outlineVariant = Cosmos,
    scrim = Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Neon01,
    onPrimary = White,
    primaryContainer = White,
    onPrimaryContainer = Black,
    inversePrimary = Neon01,
    secondary = Green04,
    onSecondary = White,
    secondaryContainer = Green01,
    onSecondaryContainer = Green04,
    tertiary = Yellow01,
    onTertiary = Black,
    tertiaryContainer = Yellow03A40,
    onTertiaryContainer = Yellow04,
    error = Red03,
    onError = White,
    errorContainer = Red01,
    onErrorContainer = Red06,
    surface = PaperGray,
    onSurface = DuskGray,
    inverseSurface = Yellow05,
    inverseOnSurface = White,
    outline = LightGray,
    outlineVariant = DarkGray,
    scrim = Black,
)

val LocalDarkTheme = compositionLocalOf { true }

val ColorScheme.primaryFixed get() = Neon05
val ColorScheme.primaryFixedDim get() = Neon01
val ColorScheme.onPrimaryFixed get() = Black
val ColorScheme.onPrimaryFixedVariable get() = Graphite

val ColorScheme.secondaryFixed get() = DarkGray
val ColorScheme.secondaryFixedDim get() = Graphite
val ColorScheme.onSecondaryFixed get() = LightGray
val ColorScheme.onSecondaryFixedVariant get() = Gray

val ColorScheme.tertiaryFixed get() = Graphite
val ColorScheme.tertiaryFixedDim get() = DuskGray
val ColorScheme.onTertiaryFixed get() = Yellow01
val ColorScheme.onTertiaryFixedVariant get() = LightGray

@Composable
fun KnightsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    CompositionLocalProvider(LocalDarkTheme provides darkTheme) {
        MaterialTheme(
            typography = Typography,
            colorScheme = colorScheme,
            content = content,
        )
    }
}
