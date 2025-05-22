package com.droidknights.app.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

val Blue01 = Color(0xFF5180FF)
val Blue01_A30 = Color(0x4D5180FF)
val Blue02 = Color(0xFF215BF6)

val Purple01 = Color(0xFFB469FF)
val Purple01_A30 = Color(0x4DB469FF)

val White = Color(0xFFFFFFFF)
val PaleGray = Color(0xFFF9F9F9)
val LightGray = Color(0xFFDCDCDC)
val DarkGray = Color(0xFF5E5E5E)
val Black = Color(0xFF000000)
val Graphite = Color(0xFF292929)

@Immutable
data class KnightsColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val primarySurface: Color,
    val onPrimarySurface: Color,
    val secondarySurface: Color,
    val onSecondarySurface: Color,
    val accentSurface: Color,
    val onAccentSurface: Color,
    val borderColor: Color,
    val selectedIconColor: Color,
    val unselectedIconColor: Color,
    val darkSurface: Color = Graphite,
    val onDarkSurface: Color = Color.White,
    val lightSurface: Color = White,
    val onLightSurface: Color = Color.Black,
) {
    companion object {
        val lightColorScheme = KnightsColorScheme(
            primary = Blue02,
            onPrimary = White,
            background = PaleGray,
            onBackground = Black,
            surface = White,
            onSurface = Black,
            primarySurface = Blue02,
            onPrimarySurface = White,
            secondarySurface = Blue01_A30,
            onSecondarySurface = Blue01,
            accentSurface = Purple01_A30,
            onAccentSurface = Purple01,
            borderColor = LightGray,
            selectedIconColor = Blue02,
            unselectedIconColor = LightGray,
        )

        val darkColorScheme = KnightsColorScheme(
            primary = Blue01,
            onPrimary = White,
            background = Black,
            onBackground = White,
            surface = Graphite,
            onSurface = White,
            primarySurface = Blue02,
            onPrimarySurface = White,
            secondarySurface = Blue01_A30,
            onSecondarySurface = White,
            accentSurface = Purple01_A30,
            onAccentSurface = Purple01,
            borderColor = DarkGray,
            selectedIconColor = Blue01,
            unselectedIconColor = DarkGray,
        )
    }
}

@Stable
private fun KnightsColorScheme.contentColorFor(backgroundColor: Color): Color =
    when (backgroundColor) {
        primary -> onPrimary
        background -> onBackground
        surface -> onSurface
        darkSurface -> onDarkSurface
        lightSurface -> onLightSurface
        primarySurface -> onPrimarySurface
        secondarySurface -> onSecondarySurface
        accentSurface -> onAccentSurface
        else -> Color.Unspecified
    }

@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color) =
    KnightsTheme.colorScheme.contentColorFor(backgroundColor).takeOrElse {
        LocalContentColor.current
    }

val LocalColorScheme = staticCompositionLocalOf { KnightsColorScheme.lightColorScheme }
