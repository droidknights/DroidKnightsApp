package com.droidknights.app.feature.contributor.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Chip
import com.droidknights.app.core.designsystem.components.ChipStyle
import com.droidknights.app.core.designsystem.components.NetworkImage
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import droidknights.feature.contributor.generated.resources.Res
import droidknights.feature.contributor.generated.resources.contributor_chip
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ContributorCard(
    showPlaceholder: Boolean,
    contributor: ContributorsUiState.Contributors.Contributor,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current
    val shimmerModifier =
        if (showPlaceholder) {
            Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = KnightsTheme.colorScheme.unselectedIconColor)
        } else {
            Modifier
        }

    KnightsCard(
        enabled = contributor.githubUrl.isNotEmpty(),
        onClick = { uriHandler.openUri(contributor.githubUrl) },
        modifier = modifier,
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp, start = 24.dp),
            ) {
                Chip(
                    text = stringResource(Res.string.contributor_chip),
                    style = ChipStyle.Border,
                )

                Text(
                    text = contributor.name,
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = KnightsTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .then(shimmerModifier),
                )

                Text(
                    text = "Lorem ipsum(id)",
                    style = KnightsTheme.typography.titleSmallM,
                    color = KnightsTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .then(shimmerModifier),
                )
            }

            NetworkImage(
                imageUrl = contributor.imageUrl,
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 24.dp, end = 24.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .then(shimmerModifier),
            )
        }
    }
}

@Composable
fun KnightsCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    color: Color = KnightsTheme.colorScheme.surface,
    contentColor: Color = KnightsTheme.colorScheme.onSurface,
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        color = color,
        contentColor = contentColor,
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 2.dp,
        content = content,
    )
}
