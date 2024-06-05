package com.droidknights.app.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedImage(
    imageRes: Int,
    onClick: () -> Unit = {},
    roundSize: Dp = 16.dp,
    border: BorderStroke? = null,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(roundSize),
        border = border,
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.aspectRatio(1f),
        )
    }
}
