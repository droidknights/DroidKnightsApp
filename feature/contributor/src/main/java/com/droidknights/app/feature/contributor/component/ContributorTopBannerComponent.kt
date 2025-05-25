package com.droidknights.app.feature.contributor.component

import android.content.res.Configuration
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.contributor.R

@Composable
internal fun ContributorTopBanner(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 148.dp)
            .statusBarsPadding()
            .padding(top = 52.dp, bottom = 18.dp)
            .padding(horizontal = 24.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.contributor_banner_title),
                style = KnightsTheme.typography.headlineSmallBL,
                color = KnightsColor.Blue02,
            )

            Text(
                text = stringResource(id = R.string.contributor_banner_description),
                style = KnightsTheme.typography.titleSmallM140,
                color = KnightsColor.Blue02,
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surfaceContainerLowest, shape = CircleShape)
                .size(88.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.icon_contributors),
                contentDescription = null,
                contentScale = ContentScale.Inside,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewContributorTopBanner() {
    KnightsTheme {
        ContributorTopBanner()
    }
}
