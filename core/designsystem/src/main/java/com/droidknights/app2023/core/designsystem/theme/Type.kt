package com.droidknights.app2023.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val SansSerifStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
)

private val DisplayLarge = SansSerifStyle.copy(
    fontSize = 57.sp,
    lineHeight = 64.sp,
    letterSpacing = (-0.25).sp
)

private val DisplayMedium = SansSerifStyle.copy(
    fontSize = 45.sp,
    lineHeight = 52.sp,
)

private val DisplaySmall = SansSerifStyle.copy(
    fontSize = 36.sp,
    lineHeight = 44.sp,
)

private val HeadlineLarge = SansSerifStyle.copy(
    fontSize = 32.sp,
    lineHeight = 40.sp,
)

val Typography.headlineLargeEB: TextStyle
    get() = HeadlineLarge.copy(fontWeight = FontWeight.ExtraBold)

val Typography.headlineLargeSB: TextStyle
    get() = HeadlineLarge.copy(fontWeight = FontWeight.SemiBold)

private val HeadlineMedium = SansSerifStyle.copy(
    fontSize = 28.sp,
    lineHeight = 36.sp,
)

val Typography.headlineMediumB: TextStyle
    get() = HeadlineMedium.copy(fontWeight = FontWeight.Bold)

val Typography.headlineMediumM: TextStyle
    get() = HeadlineMedium.copy(fontWeight = FontWeight.Medium)


private val HeadlineSmall = SansSerifStyle.copy(
    fontSize = 24.sp,
    lineHeight = 32.sp,
)

val Typography.headlineSmallBL: TextStyle
    get() = HeadlineSmall.copy(fontWeight = FontWeight.Black, letterSpacing = -(0.2.sp))

val Typography.headlineSmallM: TextStyle
    get() = HeadlineSmall.copy(fontWeight = FontWeight.Medium)

private val TitleLarge = SansSerifStyle.copy(
    fontSize = 22.sp,
    lineHeight = 28.sp,
)

val Typography.titleLargeBL: TextStyle
    get() = TitleLarge.copy(fontWeight = FontWeight.Black)

val Typography.titleLargeB: TextStyle
    get() = TitleLarge.copy(fontWeight = FontWeight.Bold)

val Typography.titleLargeM: TextStyle
    get() = TitleLarge.copy(fontWeight = FontWeight.Medium)

private val TitleMedium = SansSerifStyle.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp,
)

val Typography.titleMediumBL: TextStyle
    get() = TitleMedium.copy(fontWeight = FontWeight.Black)

val Typography.titleMediumB: TextStyle
    get() = TitleMedium.copy(fontWeight = FontWeight.Bold)

private val TitleSmall = SansSerifStyle.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp,
)

val Typography.titleSmallB: TextStyle
    get() = TitleSmall.copy(fontWeight = FontWeight.Bold)

val Typography.titleSmallM: TextStyle
    get() = TitleSmall.copy(fontWeight = FontWeight.Medium)

val Typography.titleSmallM140: TextStyle
    get() = TitleSmall.copy(fontWeight = FontWeight.Medium, lineHeight = 140.sp)

val Typography.titleSmallR140: TextStyle
    get() = TitleSmall.copy(lineHeight = 140.sp)

private val LabelLarge = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Medium,
)

private val LabelMedium = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
)

private val LabelSmall = SansSerifStyle.copy(
    fontSize = 11.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Medium,
)

private val BodyLarge = SansSerifStyle.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

private val BodyMedium = SansSerifStyle.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp
)

private val BodySmall = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp
)

val Typography = Typography(
    displayLarge = DisplayLarge,
    displayMedium = DisplayMedium,
    displaySmall = DisplaySmall,
    headlineLarge = HeadlineLarge,
    headlineMedium = HeadlineMedium,
    headlineSmall = HeadlineSmall,
    titleLarge = TitleLarge,
    titleMedium = TitleMedium,
    titleSmall = TitleSmall,
    labelLarge = LabelLarge,
    labelMedium = LabelMedium,
    labelSmall = LabelSmall,
    bodyLarge = BodyLarge,
    bodyMedium = BodyMedium,
    bodySmall = BodySmall,
)
