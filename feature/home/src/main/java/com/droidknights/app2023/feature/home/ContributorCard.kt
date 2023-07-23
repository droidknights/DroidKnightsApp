package com.droidknights.app2023.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme

@Composable
internal fun ContributorCard(
    onClick: () -> Unit,
) {
    KnightsCard(
        color = Color(0xFFEEFFE7),
        onClick = onClick,
        modifier = Modifier
            .height(164.dp),
    ) {
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img_contributor_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
            Image(
                painter = painterResource(id = R.drawable.img_wink_android),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }

        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(id = R.string.contributor_card_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 24.dp),
            )

            Text(
                text = stringResource(id = R.string.contributor_card_description),
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF52C520),
                modifier = Modifier.padding(top = 6.dp),
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
