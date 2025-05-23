package com.droidknights.app.feature.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.NetworkImage
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalContentColor
import com.droidknights.app.feature.main.model.Contributor
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun Contributors(
    title: String,
    contributors: PersistentList<Contributor>,
    onClick: (Contributor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = title,
            style = KnightsTheme.typography.headlineSmallBL,
            color = LocalContentColor.current.copy(alpha = 0.5F),
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            contributors.forEach { contributor ->
                NetworkImage(
                    imageUrl = contributor.imageUrl,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp)
                        .clickable {
                            onClick(contributor)
                        },
                )
            }
        }
    }
}
