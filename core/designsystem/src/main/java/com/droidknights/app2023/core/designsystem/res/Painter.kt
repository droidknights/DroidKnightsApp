package com.droidknights.app2023.core.designsystem.res

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.droidknights.app2023.core.designsystem.theme.LocalDarkTheme

@Composable
fun rememberPainterResource(
    @DrawableRes lightId: Int,
    @DrawableRes darkId: Int = lightId,
): Painter = painterResource(id = if (LocalDarkTheme.current) darkId else lightId)
