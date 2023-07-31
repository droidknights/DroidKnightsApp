package com.droidknights.app2023.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.model.Sponsor
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

private const val SCROLL_DELAY_MILLIS = 20L
private const val SCROLL_PIXEL_UNIT = 4f

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
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(top = 24.dp),
                )
                Text(
                    text = stringResource(
                        id = R.string.sponsor_card_description,
                        uiState.platinumCount,
                        uiState.goldCount
                    ),
                    style = KnightsTheme.typography.titleSmallR,
                    color = Color(0xFF868686),
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            if (uiState.isNotEmpty()) {
                SponsorGroup(uiState.sortedByGrade())
            }
        }
    }
}

// TODO: 세로 두 줄 고정 필요
@Composable
private fun SponsorGroup(
    sponsors: List<Sponsor>,
) {
    val scrollState = rememberLazyListState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LazyRow(
        state = scrollState,
        horizontalArrangement = Arrangement.spacedBy(
            space = 14.dp,
            alignment = Alignment.CenterHorizontally,
        ),
        userScrollEnabled = false,
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxWidth(),
    ) {
        items(count = Int.MAX_VALUE) { index ->
            SponsorLogo(sponsor = sponsors[index % sponsors.size])
        }
    }
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            while (isActive) {
                scrollState.scrollBy(SCROLL_PIXEL_UNIT)
                delay(SCROLL_DELAY_MILLIS)
            }
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
    Box(
        modifier = Modifier
            .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(42.dp))
    ) {
        NetworkImage(
            imageUrl = sponsor.imageUrl,
            placeholder = ColorPainter(Color(0xFFF9F9F9)),
            modifier = Modifier
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
