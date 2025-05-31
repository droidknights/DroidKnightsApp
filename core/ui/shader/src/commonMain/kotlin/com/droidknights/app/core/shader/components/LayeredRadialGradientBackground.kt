package com.droidknights.app.core.shader.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.core.shader.Shader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LayeredShaderBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    ShaderBackground(
        shader = LayeredRadialGradientBackground,
        modifier = modifier,
        content = content,
    )
}
private val LayeredRadialGradientBackground = object : Shader {
    override val sksl: String
        get() = """
uniform float2 uResolution;

half4 main(float2 fragCoord) {
    float2 center = float2(uResolution.x * 0.5, uResolution.y * 0.5);
    float dist = distance(fragCoord, center);
    float width = uResolution.x;
    float scale = 0.9;

    float opacity = 0.0;

    // 1st circle (0.3 * biggest radius)
    {
        float radius = width * scale * 0.3;
        float t = dist / radius;
        if (t <= 1.0) {
            opacity += smoothstep(0.6, 1.0, t) * 0.1;
        }
    }

    // 2nd circle (0.5 * biggest radius)
    {
        float radius = width * scale * 0.5 ;
        float t = dist / radius;
        if (t <= 1.0) {
            opacity += smoothstep(0.6, 1.0, t) * 0.1;
        }
    }

    // 3rd circle (1.0 * biggest radius)
    {
        float radius = width * scale * 1.0;
        float t = dist / radius;
        if (t <= 1.0) {
            opacity += smoothstep(0.6, 1.0, t) * 0.1;
        }
    }

    opacity = clamp(opacity, 0.0, 1.0);
    return half4(1.0 * opacity, 1.0 * opacity, 1.0 * opacity, opacity);
}
        """.trimIndent()
}

@Preview
@Composable
fun LayeredRadialGradientBackgroundPreview() {
    LayeredShaderBackground(modifier = Modifier.fillMaxSize()) {}
}
