package com.droidknights.app.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
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
    primary = KnightsColor.White,
    onPrimary = KnightsColor.Blue01,
    primaryContainer = KnightsColor.Graphite,
    onPrimaryContainer = KnightsColor.White,
    inversePrimary = KnightsColor.Blue02,
    secondary = KnightsColor.Blue04,
    onSecondary = KnightsColor.Blue01,
    secondaryContainer = KnightsColor.Blue04,
    onSecondaryContainer = KnightsColor.LightWhite,
    surfaceContainerLow = KnightsColor.LightWhite,
    tertiary = KnightsColor.Yellow05,
    onTertiary = KnightsColor.Yellow01,
    tertiaryContainer = KnightsColor.Yellow04,
    onTertiaryContainer = KnightsColor.White,
    error = KnightsColor.Red02,
    onError = KnightsColor.Red05,
    errorContainer = KnightsColor.Red04,
    onErrorContainer = KnightsColor.Red01,
    surface = KnightsColor.Graphite,
    onSurface = KnightsColor.White,
    onSurfaceVariant = KnightsColor.White,
    surfaceVariant = KnightsColor.White,
    surfaceDim = KnightsColor.Black,
    surfaceContainerHigh = KnightsColor.DuskGray,
    inverseSurface = KnightsColor.Neon05,
    inverseOnSurface = KnightsColor.Black,
    outline = KnightsColor.DarkGray,
    outlineVariant = KnightsColor.Cosmos,
    scrim = KnightsColor.Black,
    surfaceContainerLowest = KnightsColor.Graphite,
)

private val LightColorScheme = lightColorScheme(
    primary = KnightsColor.Neon01,
    onPrimary = KnightsColor.White,
    primaryContainer = KnightsColor.White,
    onPrimaryContainer = KnightsColor.Graphite,
    inversePrimary = KnightsColor.Neon01,
    secondary = KnightsColor.Blue04,
    onSecondary = KnightsColor.White,
    secondaryContainer = KnightsColor.Blue01,
    onSecondaryContainer = KnightsColor.LightBlack,
    surfaceContainerLow = KnightsColor.Blue01,
    tertiary = KnightsColor.Yellow01,
    onTertiary = KnightsColor.Black,
    tertiaryContainer = KnightsColor.Yellow03A40,
    onTertiaryContainer = KnightsColor.Yellow04,
    error = KnightsColor.Red03,
    onError = KnightsColor.White,
    errorContainer = KnightsColor.Red01,
    onErrorContainer = KnightsColor.Red06,
    surface = KnightsColor.White,
    onSurface = KnightsColor.Black,
    onSurfaceVariant = KnightsColor.DarkGray,
    surfaceVariant = KnightsColor.Graphite,
    surfaceDim = KnightsColor.PaleGray,
    surfaceContainerHigh = KnightsColor.LightGray,
    inverseSurface = KnightsColor.Yellow05,
    inverseOnSurface = KnightsColor.White,
    outline = KnightsColor.Gainsboro,
    outlineVariant = KnightsColor.DarkGray,
    scrim = KnightsColor.Black,
    surfaceContainerLowest = KnightsColor.PaleGray,
)

val LocalDarkTheme = compositionLocalOf { true }

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
        LocalTypography provides Typography,
        LocalShape provides KnightsShape(),
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

    val shape: KnightsShape
        @Composable
        get() = LocalShape.current
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
