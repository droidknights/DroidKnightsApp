package com.droidknights.app.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.home.R
import com.droidknights.app.feature.home.SponsorsUiStatePreviewParameterProvider
import com.droidknights.app.feature.home.model.Sponsor
import com.droidknights.app.feature.home.model.SponsorsUiState
import com.valentinilk.shimmer.shimmer

@Composable
internal fun SponsorCard(
    uiState: SponsorsUiState,
    onOrganizationSponsorClick: (String) -> Unit
) {
    when (uiState) {
        SponsorsUiState.Empty -> Unit
        SponsorsUiState.Loading -> SponsorCardSkeleton()
        is SponsorsUiState.Sponsors -> SponsorCardContents(
            onOrganizationSponsorClick = onOrganizationSponsorClick
        )
    }
}

// TODO remote에서 불러오기
@Composable
private fun SponsorCardContents(
    onOrganizationSponsorClick: (String) -> Unit,
) {
    KnightsCard {
        Box {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painterResource(R.drawable.background_home_sponsor_card),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sponsor_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = KnightsColor.Blue01,
                )

                Text(
                    text = stringResource(
                        R.string.sponsor_card_desc_template,
                        Sponsor.individuals.size
                    ),
                    style = KnightsTheme.typography.titleSmallM140,
                    color = KnightsColor.Blue01,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OrganizationSponsor(
                        sponsor = Sponsor.Organization.revenueCat,
                        onClick = onOrganizationSponsorClick,
                        logoWidth = 101.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OrganizationSponsor(
                            sponsor = Sponsor.Organization.jetBrains,
                            onClick = onOrganizationSponsorClick,
                            logoWidth = 83.dp,
                            modifier = Modifier
                                .weight(1F)
                                .height(88.dp)
                        )
                        OrganizationSponsor(
                            sponsor = Sponsor.Organization.koin,
                            onClick = onOrganizationSponsorClick,
                            logoWidth = 70.dp,
                            modifier = Modifier
                                .weight(1F)
                                .height(88.dp)
                        )
                    }
                    IndividualSponsors(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun OrganizationSponsor(
    sponsor: Sponsor.Organization,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    logoWidth: Dp = Dp.Unspecified,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable {
                onClick(sponsor.url)
            }
            .background(
                color = KnightsColor.White,
                shape = RoundedCornerShape(6.dp),
            )
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(sponsor.logoRes),
            contentDescription = null,
            modifier = Modifier
                .width(logoWidth)
                .align(Alignment.Center),
            contentScale = ContentScale.FillWidth
        )
        OrganizationSponsorTier(
            tier = sponsor.tier,
            modifier = Modifier.align(Alignment.TopStart)
        )
    }
}

@Composable
private fun OrganizationSponsorTier(
    tier: Sponsor.Organization.Tier,
    modifier: Modifier = Modifier,
) {
    when (tier) {
        Sponsor.Organization.Tier.Platinum -> {
            Image(
                painterResource(R.drawable.svg_sponsor_tier_platinum),
                contentDescription = null,
                modifier = modifier,
            )
        }
        Sponsor.Organization.Tier.Gold -> {
            Image(
                painterResource(R.drawable.svg_sponsor_tier_gold),
                contentDescription = null,
                modifier = modifier,
            )
        }
        Sponsor.Organization.Tier.Silver -> {
            Image(
                painterResource(R.drawable.svg_sponsor_tier_silver),
                contentDescription = null,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun IndividualSponsors(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = KnightsColor.White,
                shape = RoundedCornerShape(6.dp),
            ),
    ) {
        Row(
            modifier = Modifier
                .basicMarquee()
                .padding(8.dp, 11.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Sponsor.individuals.forEach {
                Text(
                    text = it.name,
                    style = KnightsTheme.typography.labelSmallM,
                    color = KnightsColor.Blue01,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun SponsorCardSkeleton(
    modifier: Modifier = Modifier,
) {
    KnightsCard(
        modifier = modifier
    ) {
        Box {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painterResource(R.drawable.background_home_sponsor_card),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )

            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                TextSkeleton(
                    modifier = Modifier
                        .size(width = 120.dp, height = 24.dp)
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))

                TextSkeleton(
                    modifier = Modifier
                        .size(width = 80.dp, height = 32.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                TextSkeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )
            }
        }
    }
}

@Composable
private fun TextSkeleton(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .shimmer()
            .background(
                color = KnightsColor.LightGray,
                shape = RoundedCornerShape(4.dp),
            )
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SponsorCardPreview(@PreviewParameter(SponsorsUiStatePreviewParameterProvider::class) sponsorsUiState: SponsorsUiState) {
    KnightsTheme {
        SponsorCard(
            uiState = sponsorsUiState,
            onOrganizationSponsorClick = {}
        )
    }
}
