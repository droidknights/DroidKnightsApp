package com.droidknights.app.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.home.R

@Composable
internal fun ContributorCard(
    onClick: () -> Unit,
) {
    KnightsCard(
        onClick = onClick,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp, bottom = 36.dp)
        ) {
            Text(
                text = stringResource(R.string.contributor_card_title),
                style = KnightsTheme.typography.headlineSmallBL,
                color = KnightsColor.Blue01,
            )
            Text(
                text = stringResource(R.string.contributor_card_description),
                style = KnightsTheme.typography.titleSmallM140,
                color = KnightsColor.Blue01,
            )
        }
    }
}

@Preview
@Composable
private fun ContributorCardPreview() {
    KnightsTheme {
        ContributorCard(
            onClick = { }
        )
    }
}
