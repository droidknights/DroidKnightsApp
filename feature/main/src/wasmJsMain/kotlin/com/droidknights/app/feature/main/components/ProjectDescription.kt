package com.droidknights.app.feature.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.main.generated.resources.Res
import droidknights.feature.main.generated.resources.ic_github
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProjectDescription(
    onGithubClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
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
}
