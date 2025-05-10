package com.droidknights.app.core.shader

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ShaderBrush
import org.jetbrains.skia.RuntimeShaderBuilder

internal class NonAndroidRuntimeEffect(shader: Shader) : RuntimeEffect {
    private val compositeRuntimeEffect = org.jetbrains.skia.RuntimeEffect.makeForShader(shader.sksl)
    private val compositeShaderBuilder = RuntimeShaderBuilder(compositeRuntimeEffect)

    override val supported: Boolean = true
    override var ready: Boolean = false

    override fun setFloatUniform(name: String, value1: Float) {
        compositeShaderBuilder.uniform(name, value1)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float) {
        compositeShaderBuilder.uniform(name, value1, value2)
    }

    override fun setFloatUniform(name: String, value1: Float, value2: Float, value3: Float) {
        compositeShaderBuilder.uniform(name, value1, value2, value3)
    }

    override fun setFloatUniform(name: String, values: FloatArray) {
        compositeShaderBuilder.uniform(name, values)
    }

    override fun update(shader: Shader, width: Float, height: Float) {
        shader.applyUniforms(this, width, height)
        ready = width > 0 && height > 0
    }

    override fun build(): Brush {
        return ShaderBrush(compositeShaderBuilder.makeShader())
    }
}

internal actual fun buildEffect(shader: Shader): RuntimeEffect {
    return NonAndroidRuntimeEffect(shader)
}
