package com.droidknights.app.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val SansSerifStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
)

internal val Typography = KnightsTypography(
    displayLargeR = SansSerifStyle.copy(
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),
    displayMediumR = SansSerifStyle.copy(
        fontSize = 45.sp,
        lineHeight = 52.sp,
    ),
    displaySmallR = SansSerifStyle.copy(
        fontSize = 36.sp,
        lineHeight = 44.sp,
    ),
    headlineLargeEB = SansSerifStyle.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.ExtraBold,
    ),
    headlineLargeSB = SansSerifStyle.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    headlineLargeR = SansSerifStyle.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
    ),
    headlineMediumB = SansSerifStyle.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Bold,
    ),
    headlineMediumM = SansSerifStyle.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Medium,
    ),
    headlineMediumR = SansSerifStyle.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp,
    ),
    headlineSmallBL = SansSerifStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = (-0.2).sp,
    ),
    headlineSmallM = SansSerifStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Medium,
    ),
    headlineSmallR = SansSerifStyle.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    titleLargeBL = SansSerifStyle.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Black,
    ),
    titleLargeB = SansSerifStyle.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleLargeM = SansSerifStyle.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
    ),
    titleLargeR = SansSerifStyle.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    titleMediumBL = SansSerifStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Black,
    ),
    titleMediumB = SansSerifStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleMediumR = SansSerifStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    titleSmallB = SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.25.sp,
    ),
    titleSmallM = SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.25.sp,
    ),
    titleSmallM140 = SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = (19.6).sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.2).sp,
    ),
    titleSmallR140 = SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = (19.6).sp,
        letterSpacing = (-0.2).sp,
    ),
    titleSmallR = SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelLargeM = SansSerifStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    labelMediumR = SansSerifStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelSmallM = SansSerifStyle.copy(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.2).sp,
    ),
    bodyLargeR = SansSerifStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMediumR = SansSerifStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    bodySmallR = SansSerifStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
)

@Immutable
data class KnightsTypography(
    val displayLargeR: TextStyle,
    val displayMediumR: TextStyle,
    val displaySmallR: TextStyle,

    val headlineLargeEB: TextStyle,
    val headlineLargeSB: TextStyle,
    val headlineLargeR: TextStyle,
    val headlineMediumB: TextStyle,
    val headlineMediumM: TextStyle,
    val headlineMediumR: TextStyle,
    val headlineSmallBL: TextStyle,
    val headlineSmallM: TextStyle,
    val headlineSmallR: TextStyle,

    val titleLargeBL: TextStyle,
    val titleLargeB: TextStyle,
    val titleLargeM: TextStyle,
    val titleLargeR: TextStyle,
    val titleMediumBL: TextStyle,
    val titleMediumB: TextStyle,
    val titleMediumR: TextStyle,
    val titleSmallB: TextStyle,
    val titleSmallM: TextStyle,
    val titleSmallM140: TextStyle,
    val titleSmallR: TextStyle,
    val titleSmallR140: TextStyle,

    val labelLargeM: TextStyle,
    val labelMediumR: TextStyle,
    val labelSmallM: TextStyle,

    val bodyLargeR: TextStyle,
    val bodyMediumR: TextStyle,
    val bodySmallR: TextStyle,
)

val LocalTypography = staticCompositionLocalOf {
    KnightsTypography(
        labelSmallM = SansSerifStyle,
        displayLargeR = SansSerifStyle,
        displayMediumR = SansSerifStyle,
        displaySmallR = SansSerifStyle,
        headlineLargeEB = SansSerifStyle,
        headlineLargeSB = SansSerifStyle,
        headlineLargeR = SansSerifStyle,
        headlineMediumB = SansSerifStyle,
        headlineMediumM = SansSerifStyle,
        headlineMediumR = SansSerifStyle,
        headlineSmallBL = SansSerifStyle,
        headlineSmallM = SansSerifStyle,
        headlineSmallR = SansSerifStyle,
        titleLargeBL = SansSerifStyle,
        titleLargeB = SansSerifStyle,
        titleLargeM = SansSerifStyle,
        titleLargeR = SansSerifStyle,
        titleMediumBL = SansSerifStyle,
        titleMediumB = SansSerifStyle,
        titleMediumR = SansSerifStyle,
        titleSmallB = SansSerifStyle,
        titleSmallM = SansSerifStyle,
        titleSmallM140 = SansSerifStyle,
        titleSmallR = SansSerifStyle,
        titleSmallR140 = SansSerifStyle,
        labelLargeM = SansSerifStyle,
        labelMediumR = SansSerifStyle,
        bodyLargeR = SansSerifStyle,
        bodyMediumR = SansSerifStyle,
        bodySmallR = SansSerifStyle,
    )
}
