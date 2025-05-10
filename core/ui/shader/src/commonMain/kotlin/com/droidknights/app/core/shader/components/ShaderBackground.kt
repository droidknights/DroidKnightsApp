package com.droidknights.app.core.shader.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import com.droidknights.app.core.shader.Shader
import com.droidknights.app.core.shader.buildEffect

@Composable
internal fun ShaderBackground(
    shader: Shader,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    BoxWithConstraints(modifier = modifier) {
        Box(
            modifier = Modifier
                .drawWithCache {
                    val runtimeEffect = buildEffect(shader).apply {
                        update(
                            shader = shader,
                            width = this@BoxWithConstraints.maxWidth.toPx(),
                            height = this@BoxWithConstraints.maxHeight.toPx(),
                        )
                    }
                    onDrawBehind {
                        drawRect(brush = runtimeEffect.build())
                    }
                }
                .fillMaxSize(),
            content = content,
        )
    }
}
