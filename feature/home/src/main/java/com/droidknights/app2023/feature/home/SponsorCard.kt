package com.droidknights.app2023.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme

@Composable
internal fun SponsorCard() {
    KnightsCard(
        modifier = Modifier
            .height(164.dp),
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(id = R.string.sponsor_card_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                // TODO: 실제 스폰서 데이터와 연결
                text = stringResource(id = R.string.sponsor_card_description, 0, 0, 0),
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF868686),
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

@Preview
@Composable
private fun SponsorCardPreview() {
    KnightsTheme {
        SponsorCard()
    }
}
