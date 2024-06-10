package com.droidknights.app.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.component.NetworkImage
import com.droidknights.app.core.designsystem.theme.DuskGray
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LightGray
import com.droidknights.app.core.designsystem.theme.White
import com.droidknights.app.core.model.Sponsor
import com.droidknights.app.feature.home.R
import com.droidknights.app.feature.home.SponsorsUiStatePreviewParameterProvider
import com.droidknights.app.feature.home.model.SponsorsUiState
import com.valentinilk.shimmer.shimmer
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun SponsorCard(uiState: SponsorsUiState) {
    when (uiState) {
        SponsorsUiState.Empty -> Unit
        SponsorsUiState.Loading -> SponsorCardSkeleton()
        is SponsorsUiState.Sponsors -> SponsorCardContents(uiState = uiState)
    }
}

@Composable
private fun SponsorCardContents(
    uiState: SponsorsUiState.Sponsors,
) {
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
                        uiState.goldCount,
                        uiState.silverCount
                    ),
                    style = KnightsTheme.typography.titleSmallM140,
                    color = DuskGray,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
            SponsorGroup(uiState.groupedSponsorsByGrade)
        }
    }
}

@Composable
private fun SponsorGroup(
    groupedSponsorsByGrade: ImmutableList<List<Sponsor>>,
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        groupedSponsorsByGrade.forEachIndexed { index, groupedSponsorsByGrade ->
            SponsorGroupRow(
                modifier = Modifier.wrapContentWidth().padding(
                    start = if (index % 2 == 0) 0.dp else 36.dp,
                    end = if (index % 2 == 0) 36.dp else 0.dp,
                ),
                sponsors = groupedSponsorsByGrade,
            )
        }
    }
}

@Composable
private fun SponsorGroupRow(
    modifier: Modifier = Modifier,
    sponsors: List<Sponsor>,
) {
    val uriHandler = LocalUriHandler.current

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 24.dp),
        userScrollEnabled = false
    ) {
        items(items = sponsors, key = { sponsor ->
            sponsor.name
        }) { sponsor ->
            SponsorLogo(sponsor = sponsor, onClick = { uriHandler.openUri(sponsor.homepage) })
        }
    }
}

@Composable
private fun SponsorLogo(
    sponsor: Sponsor,
    onClick: () -> Unit,
) {
    val gradeIcon by rememberUpdatedState(
        when (sponsor.grade) {
            Sponsor.Grade.GOLD -> R.drawable.ic_crown_gold
            Sponsor.Grade.PLATINUM -> R.drawable.ic_crown_platinum
            Sponsor.Grade.SILVER -> R.drawable.ic_crown_silver
        }
    )

    Box(
        modifier = Modifier
            .padding(horizontal = 1.dp)
    ) {
        Surface(
            onClick = onClick,
            shape = CircleShape,
            color = White,
            shadowElevation = 3.dp,
            modifier = Modifier
                .size(100.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                NetworkImage(
                    imageUrl = sponsor.imageUrl,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(80.dp)
                )
            }
        }
        Image(
            painter = painterResource(id = gradeIcon),
            contentDescription = null,
            modifier = Modifier.size(28.dp).align(Alignment.TopStart),
        )
    }
}

@Composable
private fun SponsorCardSkeleton(
    modifier: Modifier = Modifier,
) {
    KnightsCard(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
        ) {
            TextSkeleton(width = 120.dp, height = 24.dp)
            Spacer(modifier = Modifier.padding(top = 8.dp))
            TextSkeleton(width = 80.dp, height = 32.dp)

            SponsorGroupSkeleton()
        }
    }
}

@Composable
private fun TextSkeleton(
    width: Dp,
    height: Dp,
) {
    Spacer(
        modifier = Modifier
            .size(width = width, height = height)
            .fillMaxWidth()
            .shimmer()
            .background(
                color = LightGray,
                shape = RoundedCornerShape(4.dp),
            )
    )
}

@Composable
private fun SponsorGroupSkeleton(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 24.dp).fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(
                align = Alignment.Start,
                unbounded = true,
            ),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                repeat(5) {
                    SponsorLogoSkeleton()
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 24.dp),
                modifier = Modifier.padding(start = 72.dp),
            ) {
                repeat(5) {
                    SponsorLogoSkeleton()
                }
            }
        }
    }
}

@Composable
private fun SponsorLogoSkeleton(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier.size(84.dp).shimmer().background(
            color = LightGray,
            shape = CircleShape,
        ),
    )
}

@Preview
@Composable
private fun SponsorCardPreview(
    @PreviewParameter(SponsorsUiStatePreviewParameterProvider::class) sponsorsUiState: SponsorsUiState,
) {
    KnightsTheme {
        SponsorCard(
            uiState = sponsorsUiState,
        )
    }
}
