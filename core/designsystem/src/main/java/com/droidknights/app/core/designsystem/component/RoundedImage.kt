package com.droidknights.app.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
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
    size: Dp? = null,
    roundSize: Dp = 16.dp,
    border: BorderStroke? = null,
) {
    RoundedImage(
        imageRes = imageRes,
        onClick = onClick,
        width = size,
        height = size,
        roundSize = roundSize,
        border = border,
    )
}

@Composable
fun RoundedImage(
    imageRes: Int,
    onClick: () -> Unit = {},
    width: Dp? = null,
    height: Dp? = null,
    roundSize: Dp = 16.dp,
    border: BorderStroke? = null,
) {
    val modifier = if (width != null && height != null) {
        Modifier.size(width, height)
    } else {
        Modifier.aspectRatio(1f)
    }

    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(roundSize),
        border = border,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null
        )
    }
}
