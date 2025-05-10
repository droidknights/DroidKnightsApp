package com.droidknights.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalContentColor
import droidknights.composeapp.generated.resources.Res
import droidknights.composeapp.generated.resources.ic_battery
import droidknights.composeapp.generated.resources.ic_github
import droidknights.composeapp.generated.resources.ic_signal
import droidknights.composeapp.generated.resources.ic_wifi
import droidknights.composeapp.generated.resources.img_front_camera
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProjectDescription(
    onGithubClick: () -> Unit,
    modifier: Modifier = Modifier,
    app: @Composable () -> Unit,
) {
    Row(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .padding(40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Droid Knights 2025 APP (TBD)", style = KnightsTheme.typography.displayLargeR)
            Text(
                "by Compose Multiplatform",
                style = KnightsTheme.typography.displaySmallR,
            )
            Icon(
                painter = painterResource(Res.drawable.ic_github),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(onClick = onGithubClick),
            )
        }
        // 스마트폰 프레임
        Box(
            modifier = Modifier
                .padding(40.dp)
                .width(360.dp)
                .fillMaxHeight()
                .border(4.dp, KnightsTheme.colorScheme.borderColor, RoundedCornerShape(24.dp)),
        ) {
            val systemContentColor = LocalContentColor.current.copy(alpha = 0.6F)
            // 상태바
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(40.dp),
            ) {
                // 전면 카메라
                Image(
                    painter = painterResource(Res.drawable.img_front_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                )
                // 상태 아이콘
                Row(
                    modifier = Modifier.padding(end = 16.dp)
                        .align(Alignment.CenterEnd),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_wifi),
                        contentDescription = null,
                        tint = systemContentColor,
                    )
                    Icon(
                        painter = painterResource(Res.drawable.ic_signal),
                        contentDescription = null,
                        tint = systemContentColor,
                    )
                    Icon(
                        painter = painterResource(Res.drawable.ic_battery),
                        contentDescription = null,
                        tint = systemContentColor,
                    )
                }
            }
            // 드로이드나이츠 앱 슬롯
            Box(
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 20.dp)
                    .fillMaxSize(),
            ) {
                app()
            }
            // 제스처 네비게이션
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(72.dp, 2.dp)
                    .background(systemContentColor, CircleShape)
                    .align(Alignment.BottomCenter),
            )
        }
    }
}
