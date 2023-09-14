package com.droidknights.app2023.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.glance.GlanceTheme
import androidx.glance.color.ColorProvider
import androidx.glance.color.colorProviders

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

val ColorScheme.surfaceDim
    @Composable
    get() = if (LocalDarkTheme.current) Black else PaleGray

@Composable
fun KnightsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    if (!LocalInspectionMode.current) {
        val view = LocalView.current
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalTypography provides Typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

object KnightsTheme {
    val typography: KnightsTypography
        @Composable
        get() = LocalTypography.current
}

private val WidgetColorProviers = colorProviders(
    primary = ColorProvider(LightColorScheme.primary, DarkColorScheme.primary),
    onPrimary = ColorProvider(LightColorScheme.onPrimary, DarkColorScheme.onPrimary),
    primaryContainer = ColorProvider(
        LightColorScheme.primaryContainer,
        DarkColorScheme.primaryContainer
    ),
    onPrimaryContainer = ColorProvider(
        LightColorScheme.onPrimaryContainer,
        DarkColorScheme.onPrimaryContainer
    ),
    inversePrimary = ColorProvider(LightColorScheme.inversePrimary, DarkColorScheme.inversePrimary),
    secondary = ColorProvider(LightColorScheme.secondary, DarkColorScheme.secondary),
    onSecondary = ColorProvider(LightColorScheme.onSecondary, DarkColorScheme.onSecondary),
    secondaryContainer = ColorProvider(
        LightColorScheme.secondaryContainer,
        DarkColorScheme.secondaryContainer
    ),
    onSecondaryContainer = ColorProvider(
        LightColorScheme.onSecondaryContainer,
        DarkColorScheme.onSecondaryContainer
    ),
    tertiary = ColorProvider(LightColorScheme.tertiary, DarkColorScheme.tertiary),
    onTertiary = ColorProvider(LightColorScheme.onTertiary, DarkColorScheme.onTertiary),
    tertiaryContainer = ColorProvider(
        LightColorScheme.tertiaryContainer,
        DarkColorScheme.tertiaryContainer
    ),
    onTertiaryContainer = ColorProvider(
        LightColorScheme.onTertiaryContainer,
        DarkColorScheme.onTertiaryContainer
    ),
    error = ColorProvider(LightColorScheme.error, DarkColorScheme.error),
    onError = ColorProvider(LightColorScheme.onError, DarkColorScheme.onError),
    errorContainer = ColorProvider(LightColorScheme.errorContainer, DarkColorScheme.errorContainer),
    onErrorContainer = ColorProvider(
        LightColorScheme.onErrorContainer,
        DarkColorScheme.onErrorContainer
    ),
    surface = ColorProvider(LightColorScheme.surface, DarkColorScheme.surface),
    onSurface = ColorProvider(LightColorScheme.onSurface, DarkColorScheme.onSurface),
    inverseSurface = ColorProvider(LightColorScheme.inverseSurface, DarkColorScheme.inverseSurface),
    inverseOnSurface = ColorProvider(
        LightColorScheme.inverseOnSurface,
        DarkColorScheme.inverseOnSurface
    ),
    outline = ColorProvider(LightColorScheme.outline, DarkColorScheme.outline),
    background = ColorProvider(LightColorScheme.background, DarkColorScheme.background),
    onBackground = ColorProvider(LightColorScheme.onBackground, DarkColorScheme.onBackground),
    surfaceVariant = ColorProvider(LightColorScheme.surfaceVariant, DarkColorScheme.surfaceVariant),
    onSurfaceVariant = ColorProvider(
        LightColorScheme.onSurfaceVariant,
        DarkColorScheme.onSurfaceVariant
    )
)

@Composable
fun KnightsGlanceTheme(
    content: @Composable () -> Unit,
) {
    GlanceTheme(
        colors = WidgetColorProviers,
        content = content
    )
}
