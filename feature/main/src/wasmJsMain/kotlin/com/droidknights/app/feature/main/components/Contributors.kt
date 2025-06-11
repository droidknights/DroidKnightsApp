package com.droidknights.app.feature.main.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.NetworkImage
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.main.model.Contributor
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun Contributors(
    title: String,
    isMobile: Boolean,
    contributors: PersistentList<Contributor>,
    onClick: (Contributor) -> Unit,
) {
    Row(
        horizontalArrangement = if (isMobile) Arrangement.spacedBy(16.dp) else Arrangement.spacedBy(
            28.dp
        ),
    ) {
        Text(
            text = title,
            style = if (isMobile) KnightsTheme.typography.titleSmallB else KnightsTheme.typography.headlineSmallBL,
        )
        FlowRow {
            contributors.forEach { contributor ->
                NetworkImage(
                    imageUrl = contributor.imageUrl,
                    modifier = if (isMobile) Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(32.dp),
                        )
                        .clickable {
                            onClick(contributor)
                        } else
                        Modifier
                            .clip(CircleShape)
                            .size(80.dp)
                            .border(
                                width = 1.6295.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(80.dp),
                            )
                            .clickable {
                                onClick(contributor)
                            },
                )
            }
        }
    }
}
