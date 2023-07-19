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

private val HeadlineMedium = SansSerifStyle.copy(
    fontSize = 28.sp,
    lineHeight = 36.sp,
)

private val HeadlineSmall = SansSerifStyle.copy(
    fontSize = 24.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.W900,
)

private val TitleLarge = SansSerifStyle.copy(
    fontSize = 22.sp,
    lineHeight = 28.sp,
)

private val TitleMedium = SansSerifStyle.copy(
    fontSize = 16.sp,
    lineHeight = 24.sp,
)

private val TitleSmall = SansSerifStyle.copy(
    fontSize = 14.sp,
    lineHeight = 20.sp,
)

private val LabelLarge = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
)

private val LabelMedium = SansSerifStyle.copy(
    fontSize = 12.sp,
    lineHeight = 16.sp,
)

private val LabelSmall = SansSerifStyle.copy(
    fontSize = 11.sp,
    lineHeight = 16.sp,
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
