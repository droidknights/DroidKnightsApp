package com.droidknights.app.core.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import droidknights.core.designsystem.generated.resources.Res
import droidknights.core.designsystem.generated.resources.playstore_m
import org.jetbrains.compose.resources.painterResource

@Composable
fun NetworkImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
) {
    val painter = placeholder ?: painterResource(Res.drawable.playstore_m)

    AsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        modifier = modifier,
        placeholder = placeholder,
        contentScale = contentScale,
        contentDescription = contentDescription,
        error = painter
    )
}
