package com.droidknights.app.feature.main.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.NetworkImage
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.main.model.Contributor
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun MobileContributors(
    title: String,
    contributors: PersistentList<Contributor>,
    onClick: (Contributor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = title,
            style = KnightsTheme.typography.titleSmallB,
        )
        FlowRow {
            contributors.forEach { contributor ->
                NetworkImage(
                    imageUrl = contributor.imageUrl,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .border(
                            width = 1.dp,
                            color = KnightsTheme.colorScheme.onBackground,
                            shape = RoundedCornerShape(32.dp)
                        )
                        .clickable {
                            onClick(contributor)
                        },
                )
            }
        }
    }
}
