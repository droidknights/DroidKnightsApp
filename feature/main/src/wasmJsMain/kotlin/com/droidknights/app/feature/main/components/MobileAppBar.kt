package com.droidknights.app.feature.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme

@Composable
internal fun MobileAppBar(
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.Center,
    ) {
        Column {
            Text(
                text = "2025 Conference APP",
                style = KnightsTheme.typography.headlineMediumB,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "드로이드나이츠 앱의 컨트리뷰터가 되어주세요!",
                style = KnightsTheme.typography.titleMediumB,
            )
        }
    }
}
