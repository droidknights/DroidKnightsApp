package com.droidknights.app2023.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.model.Sponsor

@Composable
fun SponsorCard(
    // TODO: 실제 데이터와 연결
    uiState: SponsorsUiState.Sponsors = SponsorsUiState.Sponsors(emptyList()),
) {
    KnightsCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sponsor_card_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 24.dp),
            )
            Text(
                text = stringResource(
                    id = R.string.sponsor_card_description,
                    uiState.platinumCount,
                    uiState.diaCount,
                    uiState.goldCount
                ),
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF868686),
                modifier = Modifier.padding(top = 8.dp),
            )
            SponsorGroup(uiState.sponsors)
        }
    }
}

// TODO: 이미지 연결 및 동적으로 배치
@Composable
private fun SponsorGroup(
    sponsors: List<Sponsor>,
) {
    Row(
        horizontalArrangement = Arrangement
            .spacedBy(
                space = 8.dp,
                alignment = Alignment.CenterHorizontally,
            ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {
        sponsors.forEach {
            SponsorLogo(it)
        }
    }
}

@Composable
private fun SponsorLogo(
    sponsor: Sponsor,
) {
    val gradeIcon = when (sponsor.grade) {
        Sponsor.Grade.GOLD -> R.drawable.ic_crown_gold
        Sponsor.Grade.PLATINUM -> R.drawable.ic_crown_platinum
        Sponsor.Grade.DIA -> R.drawable.ic_crown_dia
    }
    Box(
        modifier = Modifier
            .size(84.dp)
            .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(42.dp))
    ) {
        Image(
            painter = painterResource(id = gradeIcon),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.TopStart),
        )
    }
}

@Preview
@Composable
private fun SponsorCardPreview() {
    KnightsTheme {
        SponsorCard(
            listOf(
                Sponsor(
                    name = "Sponsor1",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.GOLD,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
                Sponsor(
                    name = "Sponsor2",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.PLATINUM,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
                Sponsor(
                    name = "Sponsor3",
                    homepage = "https://www.instagram.com/droid_knights",
                    grade = Sponsor.Grade.DIA,
                    imageUrl = "https://avatars.githubusercontent.com/u/25101514",
                ),
            ).let(SponsorsUiState::Sponsors)
        )
    }
}
