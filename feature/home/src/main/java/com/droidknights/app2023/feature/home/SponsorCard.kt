package com.droidknights.app2023.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
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
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.component.NetworkImage
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.model.Sponsor
import com.droidknights.app2023.core.ui.observeAsState
import kotlinx.coroutines.delay

private const val SCROLL_DELAY_MILLIS = 20L
private const val SCROLL_PIXEL_UNIT = 4f

@Composable
fun SponsorCard(
    // TODO: 실제 데이터와 연결
    uiState: SponsorsUiState =
        listOf(
            Sponsor(
                name = "Sponsor1",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.GOLD,
                imageUrl = "https://picsum.photos/id/237/200/200",
            ),
            Sponsor(
                name = "Sponsor2",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/204/200/200",
            ),
            Sponsor(
                name = "Sponsor3",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/203/200/200",
            ),
            Sponsor(
                name = "Sponsor4",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/202/200/200",
            ),
            Sponsor(
                name = "Sponsor5",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/201/200/200",
            ),
            Sponsor(
                name = "Sponsor6",
                homepage = "https://www.instagram.com/droid_knights",
                grade = Sponsor.Grade.PLATINUM,
                imageUrl = "https://picsum.photos/id/200/200/200",
            ),
        ).let(::SponsorsUiState),
) {
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
            SponsorGroup(uiState.sponsors)
        }
    }
}

// TODO: 세로 두 줄 고정 필요
@Composable
private fun SponsorGroup(
    sponsors: List<Sponsor>,
) {
    val itemsState = remember { sponsors.toMutableStateList() }
    val scrollState = rememberLazyListState()
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()

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
        items(itemsState, key = { it.name }) {
            SponsorLogo(it)
        }
    }
    LaunchedEffect(scrollState.canScrollForward) {
        if (scrollState.canScrollForward) return@LaunchedEffect
        itemsState.addAll(sponsors)
    }
    LaunchedEffect(lifecycleState.value) {
        when (lifecycleState.value) {
            Lifecycle.Event.ON_RESUME -> {
                while (true) {
                    autoScroll(scrollState)
                }
            }

            else -> {}
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

private suspend fun autoScroll(lazyListState: LazyListState) {
    lazyListState.scroll {
        scrollBy(SCROLL_PIXEL_UNIT)
    }
    delay(SCROLL_DELAY_MILLIS)
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
