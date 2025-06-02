package com.droidknights.app.feature.session.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.session.generated.resources.Res
import droidknights.feature.session.generated.resources.session_overview_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun SessionOverview(content: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(resource = Res.string.session_overview_title),
            style = KnightsTheme.typography.titleSmallB,
            color = KnightsTheme.colorScheme.onSurface,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = content,
            style = KnightsTheme.typography.titleSmallR140,
            color = KnightsTheme.colorScheme.onSurface,
        )
    }
}
