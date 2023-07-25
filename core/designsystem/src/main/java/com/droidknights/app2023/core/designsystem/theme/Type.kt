package com.droidknights.app2023.core.designsystem.theme

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

private val DisplayLargeR = SansSerifStyle.copy(
    fontSize = 57.sp,
    lineHeight = 64.sp,
    letterSpacing = (-0.25).sp
)

private val DisplayMediumR = SansSerifStyle.copy(
    fontSize = 45.sp,
    lineHeight = 52.sp,
)

private val DisplaySmallR = SansSerifStyle.copy(
    fontSize = 36.sp,
    lineHeight = 44.sp,
)

private val HeadlineLargeEB: TextStyle
    get() = HeadlineLargeR.copy(fontWeight = FontWeight.ExtraBold)

private val HeadlineLargeSB: TextStyle
    get() = HeadlineLargeR.copy(fontWeight = FontWeight.SemiBold)

private val HeadlineLargeR = SansSerifStyle.copy(
    fontSize = 32.sp,
    lineHeight = 40.sp,
)

private val HeadlineMediumB: TextStyle
    get() = HeadlineMediumR.copy(fontWeight = FontWeight.Bold)

private val HeadlineMediumM: TextStyle
    get() = HeadlineMediumR.copy(fontWeight = FontWeight.Medium)


private val HeadlineMediumR = SansSerifStyle.copy(
    fontSize = 28.sp,
    lineHeight = 36.sp,
)

private val HeadlineSmallBL: TextStyle
    get() = HeadlineSmallR.copy(fontWeight = FontWeight.Black, letterSpacing = -(0.2.sp))

private val HeadlineSmallM: TextStyle
    get() = HeadlineSmallR.copy(fontWeight = FontWeight.Medium)

private val HeadlineSmallR = SansSerifStyle.copy(
    fontSize = 24.sp,
    lineHeight = 32.sp,
)

private val TitleLargeBL: TextStyle
    get() = TitleLargeR.copy(fontWeight = FontWeight.Black)

private val TitleLargeB: TextStyle
    get() = TitleLargeR.copy(fontWeight = FontWeight.Bold)

private val TitleLargeM: TextStyle
    get() = TitleLargeR.copy(fontWeight = FontWeight.Medium)

private val TitleLargeR = SansSerifStyle.copy(
    fontSize = 22.sp,
    lineHeight = 28.sp,
)

private val TitleMediumBL: TextStyle
    get() = TitleMediumR.copy(fontWeight = FontWeight.Black)

private val TitleMediumB: TextStyle
    get() = TitleMediumR.copy(fontWeight = FontWeight.Bold)

private val TitleMediumR = SansSerifStyle.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp,
)

private val TitleSmallB: TextStyle
    get() = TitleSmallR.copy(fontWeight = FontWeight.Bold)

private val TitleSmallM: TextStyle
    get() = TitleSmallR.copy(fontWeight = FontWeight.Medium)

private val TitleSmallM140: TextStyle
    get() = TitleSmallR.copy(fontWeight = FontWeight.Medium, lineHeight = 140.sp)

private val TitleSmallR140: TextStyle
    get() = TitleSmallR.copy(lineHeight = 140.sp)

private val TitleSmallR = SansSerifStyle.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp,
)

private val LabelLargeM = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Medium,
)

private val LabelMediumR = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
)

private val LabelSmallM = SansSerifStyle.copy(
    fontSize = 11.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Medium,
)

private val BodyLargeR = SansSerifStyle.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

private val BodyMediumR = SansSerifStyle.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp
)

private val BodySmallR = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp
)

val Typography = KnightsTypography(
    displayLargeR = DisplayLargeR,
    displayMediumR = DisplayMediumR,
    displaySmallR = DisplaySmallR,
    headlineLargeEB = HeadlineLargeEB,
    headlineLargeSB = HeadlineLargeSB,
    headlineLargeR = HeadlineLargeR,
    headlineMediumB = HeadlineMediumB,
    headlineMediumM = HeadlineMediumM,
    headlineMediumR = HeadlineMediumR,
    headlineSmallBL = HeadlineSmallBL,
    headlineSmallM = HeadlineSmallM,
    headlineSmallR = HeadlineSmallR,
    titleLargeBL = TitleLargeBL,
    titleLargeB = TitleLargeB,
    titleLargeM = TitleLargeM,
    titleLargeR = TitleLargeR,
    titleMediumBL = TitleMediumBL,
    titleMediumB = TitleMediumB,
    titleMediumR = TitleMediumR,
    titleSmallB = TitleSmallB,
    titleSmallM = TitleSmallM,
    titleSmallM140 = TitleSmallM140,
    titleSmallR140 = TitleSmallR140,
    titleSmallR = TitleSmallR,
    labelLargeM = LabelLargeM,
    labelMediumR = LabelMediumR,
    labelSmallM = LabelSmallM,
    bodyLargeR = BodyLargeR,
    bodyMediumR = BodyMediumR,
    bodySmallR = BodySmallR,
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
        labelSmallM = TextStyle(),
        displayLargeR = TextStyle(),
        displayMediumR = TextStyle(),
        displaySmallR = TextStyle(),
        headlineLargeEB = TextStyle(),
        headlineLargeSB = TextStyle(),
        headlineLargeR = TextStyle(),
        headlineMediumB = TextStyle(),
        headlineMediumM = TextStyle(),
        headlineMediumR = TextStyle(),
        headlineSmallBL = TextStyle(),
        headlineSmallM = TextStyle(),
        headlineSmallR = TextStyle(),
        titleLargeBL = TextStyle(),
        titleLargeB = TextStyle(),
        titleLargeM = TextStyle(),
        titleLargeR = TextStyle(),
        titleMediumBL = TextStyle(),
        titleMediumB = TextStyle(),
        titleMediumR = TextStyle(),
        titleSmallB = TextStyle(),
        titleSmallM = TextStyle(),
        titleSmallM140 = TextStyle(),
        titleSmallR = TextStyle(),
        titleSmallR140 = TextStyle(),
        labelLargeM = TextStyle(),
        labelMediumR = TextStyle(),
        bodyLargeR = TextStyle(),
        bodyMediumR = TextStyle(),
        bodySmallR = TextStyle(),
    )
}
