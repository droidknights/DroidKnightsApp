package com.droidknights.app.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class KnightsTypography(
    val default: TextStyle,

    val displayLargeR: TextStyle = default.copy(
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),
    val displayMediumR: TextStyle = default.copy(
        fontSize = 45.sp,
        lineHeight = 52.sp,
    ),

    val displayMediumEB: TextStyle = default.copy(
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.ExtraBold,
    ),
    val displaySmallR: TextStyle = default.copy(
        fontSize = 36.sp,
        lineHeight = 44.sp,
    ),

    val headlineLargeEB: TextStyle = default.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.ExtraBold,
    ),
    val headlineLargeSB: TextStyle = default.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    val headlineLargeR: TextStyle = default.copy(
        fontSize = 32.sp,
        lineHeight = 40.sp,
    ),
    val headlineMediumB: TextStyle = default.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Bold,
    ),
    val headlineMediumM: TextStyle = default.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Medium,
    ),
    val headlineMediumR: TextStyle = default.copy(
        fontSize = 28.sp,
        lineHeight = 36.sp,
    ),
    val headlineSmallBL: TextStyle = default.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = (-0.2).sp,
    ),
    val headlineSmallM: TextStyle = default.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.Medium,
    ),
    val headlineSmallR: TextStyle = default.copy(
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),

    val titleLargeBL: TextStyle = default.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Black,
    ),
    val titleLargeB: TextStyle = default.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
    ),
    val titleLargeM: TextStyle = default.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
    ),
    val titleLargeR: TextStyle = default.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    val titleMediumBL: TextStyle = default.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Black,
    ),
    val titleMediumB: TextStyle = default.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    val titleMediumR: TextStyle = default.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    val titleSmallB: TextStyle = default.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.25.sp,
    ),
    val titleSmallM: TextStyle = default.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.25.sp,
    ),
    val titleSmallM140: TextStyle = default.copy(
        fontSize = 14.sp,
        lineHeight = (19.6).sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.2).sp,
    ),
    val titleSmallR: TextStyle = default.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    val titleSmallR140: TextStyle = default.copy(
        fontSize = 14.sp,
        lineHeight = (19.6).sp,
        letterSpacing = (-0.2).sp,
    ),

    val labelLargeM: TextStyle = default.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
    ),
    val labelMediumR: TextStyle = default.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    val labelSmallM: TextStyle = default.copy(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.2).sp,
    ),

    val bodyLargeR: TextStyle = default.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    val bodyMediumR: TextStyle = default.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    val bodySmallR: TextStyle = default.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
) {
    companion object {
        fun with(
            fontFamily: FontFamily = FontFamily.Default,
            fontWeight: FontWeight = FontWeight.Normal,
        ) = KnightsTypography(
            default = TextStyle(
                fontFamily = fontFamily,
                fontWeight = fontWeight,
            ),
        )
    }
}

internal val LocalTypography = staticCompositionLocalOf<KnightsTypography> {
    error("KnightsTypography를 provide 해야합니다.")
}
