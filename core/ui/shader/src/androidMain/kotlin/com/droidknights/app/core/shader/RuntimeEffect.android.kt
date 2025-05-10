package com.droidknights.app.core.shader

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush

/**
 * No-op implementation of the Runtime effect for devices not supporting the [RuntimeShader].
 */
internal class FallbackAndroidRuntimeEffect : RuntimeEffect {

    override val supported: Boolean = false
    override var ready: Boolean = false

    override fun build(): Brush {
        return Brush.horizontalGradient(listOf(Color.White, Color.White))
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
internal class AndroidRuntimeEffect(shader: Shader) : RuntimeEffect {
    private val compositeRuntimeEffect = RuntimeShader(shader.sksl)

    override val supported: Boolean = true
    override var ready: Boolean = false

    override fun setFloatUniform(name: String, value1: Float) {
        compositeRuntimeEffect.setFloatUniform(name, value1)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float) {
        compositeRuntimeEffect.setFloatUniform(name, value1, value2)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float) {
        compositeRuntimeEffect.setFloatUniform(name, value1, value2, value3)
    }

    override fun setFloatUniform(name: String, values: FloatArray) {
        compositeRuntimeEffect.setFloatUniform(name, values)
    }

    override fun update(shader: Shader, width: Float, height: Float) {
        shader.applyUniforms(this, width, height)
        ready = width > 0 && height > 0
    }

    override fun build(): Brush {
        return ShaderBrush(compositeRuntimeEffect)
    }
}

internal actual fun buildEffect(shader: Shader): RuntimeEffect {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        AndroidRuntimeEffect(shader)
    } else {
        FallbackAndroidRuntimeEffect()
    }
}
