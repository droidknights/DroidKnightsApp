package com.droidknights.app2023.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.theme.DuskGray
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.PaleGray
import com.droidknights.app2023.core.model.Sponsor

@Composable
internal fun SponsorCard(uiState: SponsorsUiState) {
    KnightsCard {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = stringResource(id = R.string.sponsor_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(top = 24.dp),
                )
                Text(
                    text = stringResource(
                        id = R.string.sponsor_card_description,
                        uiState.platinumCount,
                        uiState.goldCount
                    ),
                    style = KnightsTheme.typography.titleSmallR,
                    color = DuskGray,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            SponsorGroup(uiState.sponsors)
        }
    }
}

@Composable
private fun SponsorGroup(
    sponsors: List<Sponsor>,
) {
    val platinumSponsors = sponsors.filter { it.grade == Sponsor.Grade.PLATINUM }
    val goldSponsors = sponsors.filter { it.grade == Sponsor.Grade.GOLD }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        SponsorGroupRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 21.dp,
                    end = 24.dp
                ),
            sponsors = platinumSponsors
        )

        SponsorGroupRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 69.dp,
                    end = 24.dp
                ),
            sponsors = goldSponsors
        )
    }
}

@Composable
private fun SponsorGroupRow(
    modifier: Modifier = Modifier,
    sponsors: List<Sponsor>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 11.dp),
    ) {
        items(
            items = sponsors,
            key = { sponsor ->
                sponsor.name
            }
        ) { sponsor ->
            SponsorLogo(sponsor = sponsor)
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
    }
    Box(modifier = Modifier.padding(start = 3.dp)) {
        NetworkImage(
            imageUrl = sponsor.imageUrl,
            placeholder = ColorPainter(PaleGray),
            modifier = Modifier
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape
                )
                .size(84.dp)
                .clip(CircleShape)
        )
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
            ).let(::SponsorsUiState)
        )
    }
}
