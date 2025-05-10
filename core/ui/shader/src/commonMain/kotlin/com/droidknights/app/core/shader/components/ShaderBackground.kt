package com.droidknights.app.core.shader.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
    Box(
        modifier = modifier
            .drawWithCache {
                val runtimeEffect = buildEffect(shader)
                val brush = runtimeEffect.build()

                onDrawBehind {
                    runtimeEffect.update(shader, size.width, size.height)
                    drawRect(brush = brush)
                }
            },
        content = content,
    )
}
