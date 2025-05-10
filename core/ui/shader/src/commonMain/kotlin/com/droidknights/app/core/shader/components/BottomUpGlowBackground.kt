package com.droidknights.app.core.shader.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.core.shader.Shader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BottomUpGlowBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    ShaderBackground(
        shader = BottomUpGlowShader,
        modifier = modifier,
        content = content,
    )
}

private val BottomUpGlowShader = object : Shader {
    override val sksl: String
        get() = """
uniform float2 uResolution;

half4 main(float2 fragCoord) {
    vec2 center = vec2(uResolution.x * 0.5, uResolution.y * 2);
    float dist = distance(fragCoord, center);

    // 4개의 반지름 (중심과 끝점으로 원 반지름 계산)
    float radius = distance(uResolution, center);
    float r1 = radius * 0.35;
    float r2 = radius * 0.7;
    float r3 = radius * 1.0;
    float r4 = radius * 1.2;

    float blur = uResolution.y * 0.03;

    // 색상 정의 (파란색 계열 4단계)
    vec3 c1 = vec3(0.1216, 0.3333, 0.9059); // #1F55E7
    vec3 c2 = vec3(0.3176, 0.5020, 1.0000); // #5180FF
    vec3 c3 = vec3(0.1294, 0.3569, 0.9647); // #215BF6
    vec3 c4 = vec3(0.3176, 0.5020, 1.0000); // #5180FF

    vec3 color;

    if (dist <= r1) {
        color = c1;
    } else if (dist <= r2) {
        float t = smoothstep(r1, r2, dist);
        color = mix(c1, c2, t);
    } else if (dist <= r3) {
        float t = smoothstep(r2, r3, dist);
        color = mix(c2, c3, t);
    } else if (dist <= r4) {
        float t = smoothstep(r3, r4, dist);
        color = mix(c3, c4, t);
    } else {
        color = c4; // r4 바깥은 고정된 solid 색상
    }

    return half4(color, 1.0);
}
        """.trimIndent()
}

@Preview
@Composable
fun BottomUpGlowBackgroundPreview() {
    BottomUpGlowBackground(
        modifier = Modifier.fillMaxSize(),
    ) {}
}
