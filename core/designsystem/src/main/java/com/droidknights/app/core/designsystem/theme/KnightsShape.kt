package com.droidknights.app.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

object KnightsShape {

    @Stable
    val rounded12 = RoundedCornerShape(12.dp)
}

val LocalShape = staticCompositionLocalOf { KnightsShape }
