package com.droidknights.app.feature.contributor.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.component.NetworkImage
import com.droidknights.app.core.designsystem.component.TextChip
import com.droidknights.app.core.designsystem.res.rememberPainterResource
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.contributor.R
import com.droidknights.app.feature.contributor.model.ContributorsUiState
import com.valentinilk.shimmer.shimmer

@Composable
internal fun ContributorCard(
    contributor: ContributorsUiState.Contributors.Item.User,
    modifier: Modifier = Modifier,
    showPlaceholder: Boolean,
) {
    val uriHandler = LocalUriHandler.current
    val shimmerModifier = if (showPlaceholder) {
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .shimmer()
            .background(color = MaterialTheme.colorScheme.outline)
    } else {
        Modifier
    }

    val placeholder = rememberPainterResource(
        lightId = R.drawable.ic_contributor_placeholder_lightmode,
        darkId = R.drawable.ic_contributor_placeholder_darkmode,
    )

    KnightsCard(
        enabled = contributor.githubUrl.isNotEmpty(),
        onClick = { uriHandler.openUri(contributor.githubUrl) },
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
                    text = contributor.name,
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .then(shimmerModifier)
                )
            }

            NetworkImage(
                imageUrl = contributor.imageUrl,
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContributorCardPreview() {
    KnightsTheme {
        ContributorCard(
            contributor = ContributorsUiState.Contributors.Item.User(
                id = 0L,
                name = "Contributor1",
                imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                githubUrl = "https://github.com/droidknights",
            ),
            showPlaceholder = true,
        )
        ContributorCard(
            contributor = ContributorsUiState.Contributors.Item.User(
                id = 0L,
                name = "Contributor1",
                imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                githubUrl = "https://github.com/droidknights",
            ),
            showPlaceholder = false,
        )
    }
}
