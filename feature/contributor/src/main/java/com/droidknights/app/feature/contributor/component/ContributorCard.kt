package com.droidknights.app.feature.contributor.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.component.NetworkImage
import com.droidknights.app.core.designsystem.component.TextChip
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.Contributor
import com.droidknights.app.feature.contributor.R

@Composable
internal fun ContributorCard(
    contributor: Contributor?,
    uriHandler: UriHandler,
    modifier: Modifier,
    shimmerModifier: Modifier,
    placeholder: Painter
) {
    KnightsCard(
        enabled = contributor?.githubUrl?.isNotEmpty() ?: false,
        onClick = { uriHandler.openUri(contributor?.githubUrl ?: return@KnightsCard) },
        modifier = modifier,
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 24.dp,
                        end = 16.dp
                    )
            ) {
                TextChip(
                    stringResource(id = R.string.contributor_chip),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    labelColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = shimmerModifier
                )
                Text(
                    text = contributor?.name ?: " ".repeat(20),
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .then(shimmerModifier)
                )
            }

            NetworkImage(
                imageUrl = contributor?.imageUrl,
                placeholder = placeholder,
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .then(shimmerModifier)
            )
        }
    }
}
