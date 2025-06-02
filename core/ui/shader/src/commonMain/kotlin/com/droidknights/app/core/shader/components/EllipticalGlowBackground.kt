package com.droidknights.app.core.shader.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.shader.Shader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EllipticalGlowBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    ShaderBackground(
        shader = EllipticalGlowShader,
        modifier = modifier,
        content = content,
    )
}

private val EllipticalGlowShader = object : Shader {
    override val sksl: String
        get() = """
uniform vec2 uResolution;

vec4 main(vec2 fragCoord) {
    // 정규화된 좌표
    vec2 uv = fragCoord / uResolution;
    
    // 투명 배경
    vec4 color = vec4(0.0, 0.0, 0.0, 0.0);
    
    // 타원 규격
    float ellipseWidth = uResolution.x;
    float ellipseHeight = uResolution.y;
    vec2 canvasCenter = vec2((uResolution.x / 2.0) * 1.3, uResolution.y * 1.2);
    
    float scaleX = ellipseWidth / ellipseHeight;
    float radius1 = (ellipseHeight / 2.0) * 2.0;
    float radius2 = radius1 * 1.3;
    
    float rotationAngle = radians(180);
    
    // 현재 픽셀을 canvasCenter 기준으로 회전
    vec2 rotatedCoord = fragCoord - canvasCenter;
    float cosA = cos(-rotationAngle);
    float sinA = sin(-rotationAngle);
    rotatedCoord = vec2(
        rotatedCoord.x * cosA - rotatedCoord.y * sinA,
        rotatedCoord.x * sinA + rotatedCoord.y * cosA
    ) + canvasCenter;
    
    // 타원형 변형 적용 (scaleX)
    vec2 ellipticalCoord = (rotatedCoord - canvasCenter) / vec2(scaleX, 1.0) + canvasCenter;
    
    // 색상 정의 (정확한 파란색 계열)
    vec3 blue01 = vec3(81.0/255.0, 128.0/255.0, 255.0/255.0);  // #5180FF
    vec3 blue02 = vec3(33.0/255.0, 91.0/255.0, 246.0/255.0);   // #215BF6
    
    float dist = distance(ellipticalCoord, canvasCenter);
    
    // 매우 부드러운 하나의 그라디언트로 처리
    if (dist <= radius2) {
        float normalizedDist = dist / radius2;
        
        // 매우 부드러운 색상 전환을 위한 여러 단계의 smoothstep
        float colorMix1 = 1.0 - smoothstep(0.0, 0.8, normalizedDist);
        float colorMix2 = smoothstep(0.2, 0.8, normalizedDist);
        
        vec3 centerColor = blue02;
        vec3 midColor = mix(blue02, blue01, 0.6);
        vec3 edgeColor = mix(blue01, blue02, 0.3);
        
        vec3 finalColor = mix(
            mix(centerColor, midColor, colorMix2),
            edgeColor,
            smoothstep(0.3, 1.0, normalizedDist)
        );
        
        // 매우 부드러운 알파 전환
        float finalAlpha = 1.0 - smoothstep(0.4, 1.0, normalizedDist);
        finalAlpha = smoothstep(0.0, 1.0, finalAlpha); // 추가 부드러움
        
        color = vec4(finalColor * finalAlpha, finalAlpha);
    }
    
    return color;
}
        """.trimIndent()
}

@Preview
@Composable
fun RotatedEllipseCanvasPreview() {
    EllipticalGlowBackground(modifier = Modifier.size(350.dp)) {
        Box(modifier = Modifier.width(500.dp).height(250.dp))
    }
}
