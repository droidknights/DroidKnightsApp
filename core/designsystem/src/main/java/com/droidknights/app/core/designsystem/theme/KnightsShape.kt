package com.droidknights.app.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Stable
data class KnightsShape(
    val chip: Shape = RoundedCornerShape(10.dp),
    val rounded12: Shape = RoundedCornerShape(12.dp),
)

val LocalShape = staticCompositionLocalOf { KnightsShape() }
