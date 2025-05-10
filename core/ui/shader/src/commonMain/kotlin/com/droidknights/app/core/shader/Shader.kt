package com.droidknights.app.core.shader

internal interface Shader {
    val sksl: String

    fun applyUniforms(runtimeEffect: RuntimeEffect, width: Float, height: Float) {
        runtimeEffect.setFloatUniform("uResolution", width, height)
    }
}
