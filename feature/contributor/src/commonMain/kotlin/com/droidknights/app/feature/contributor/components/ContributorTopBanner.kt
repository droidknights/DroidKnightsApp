package com.droidknights.app.feature.contributor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.contributor.generated.resources.Res
import droidknights.feature.contributor.generated.resources.contributor_banner_description
import droidknights.feature.contributor.generated.resources.contributor_banner_title
import droidknights.feature.contributor.generated.resources.ic_contributors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ContributorTopBanner(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 148.dp)
            .statusBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 32.dp,
                    top = 24.5.dp,
                    bottom = 36.5.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Text(
                text = stringResource(resource = Res.string.contributor_banner_title),
                style = KnightsTheme.typography.headlineSmallBL,
                color = KnightsTheme.colorScheme.primary,
            )

            Text(
                text = stringResource(resource = Res.string.contributor_banner_description),
                style = KnightsTheme.typography.titleSmallM140,
                color = KnightsTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 3.dp),
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 18.5.dp, end = 24.dp, bottom = 41.5.dp)
                .background(color = KnightsTheme.colorScheme.avatarBackground, shape = CircleShape)
                .size(88.dp),
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_contributors),
                contentDescription = null,
                contentScale = ContentScale.Inside,
            )
        }
    }
}
