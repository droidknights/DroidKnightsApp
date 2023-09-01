package com.droidknights.app2023.feature.tvsession.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun KnightsCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        colors = NonInteractiveSurfaceDefaults.colors(containerColor = color),
        shape = RoundedCornerShape(32.dp),
        tonalElevation = 2.dp,
        content = content,
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun KnightsCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        colors = ClickableSurfaceDefaults.colors(
            containerColor = color,
            focusedContainerColor = MaterialTheme.colorScheme.inversePrimary
        ),
        shape = ClickableSurfaceDefaults.shape(RoundedCornerShape(32.dp)),
        tonalElevation = 2.dp,
        content = content,
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun KnightsCardPreview() {
    KnightsTheme {
        KnightsCard(modifier = Modifier.size(320.dp, 160.dp), content = { })
    }
}
