package com.droidknights.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.Blue01
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.shader.components.EllipticalGlowBackground
import com.droidknights.app.feature.home.model.Sponsor
import droidknights.feature.home.generated.resources.Res
import droidknights.feature.home.generated.resources.home_sponsor_card_desc_gold_template
import droidknights.feature.home.generated.resources.home_sponsor_card_desc_individual_template
import droidknights.feature.home.generated.resources.home_sponsor_card_desc_platinum_template
import droidknights.feature.home.generated.resources.home_sponsor_card_title
import droidknights.feature.home.generated.resources.sponsor_logo_jetbrains
import droidknights.feature.home.generated.resources.sponsor_logo_revenue_cat
import droidknights.feature.home.generated.resources.svg_sponsor_tier_gold
import droidknights.feature.home.generated.resources.svg_sponsor_tier_platinum
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

// TODO: 스폰서 목록을 api로 가져오기
@Composable
fun HomeSponsorCard(
    onOrganizationSponsorClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val description = run {
        val organizationDescriptions = organizationSponsors
            .groupBy { it.tier }
            .mapValues { (_, sponsors) -> sponsors.size }
            .mapNotNull { (tier, count) ->
                when (tier) {
                    Sponsor.Organization.Tier.Platinum -> stringResource(
                        Res.string.home_sponsor_card_desc_platinum_template,
                        count,
                    )
                    Sponsor.Organization.Tier.Gold -> stringResource(
                        Res.string.home_sponsor_card_desc_gold_template,
                        count,
                    )
                }
            }

        val individualDescription = listOfNotNull(
            individualSponsors
                .takeIf { it.isNotEmpty() }
                ?.let {
                    stringResource(
                        Res.string.home_sponsor_card_desc_individual_template,
                        it.size,
                    )
                },
        )

        (organizationDescriptions + individualDescription)
            .joinToString(separator = ", ")
    }

    Surface(
        modifier = modifier,
        contentColor = KnightsTheme.colorScheme.primary,
        shape = RoundedCornerShape(16.dp),
    ) {
        EllipticalGlowBackground {
            Column(
                modifier = Modifier.padding(24.dp),
            ) {
                Text(
                    text = stringResource(Res.string.home_sponsor_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = KnightsTheme.typography.titleSmallM140,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    organizationSponsors.forEach {
                        OrganizationSponsor(
                            sponsor = it,
                            onClick = {
                                onOrganizationSponsorClick(it.url)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(72.dp),
                        )
                    }
                    IndividualSponsors(
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
private fun OrganizationSponsor(
    sponsor: Sponsor.Organization,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        color = KnightsTheme.colorScheme.lightSurface,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Image(
                painter = painterResource(sponsor.logoRes),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .align(Alignment.Center),
            )
            OrganizationSponsorTier(
                tier = sponsor.tier,
                modifier = Modifier.align(Alignment.TopStart),
            )
        }
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
                painterResource(Res.drawable.svg_sponsor_tier_platinum),
                contentDescription = null,
                modifier = modifier,
            )
        }
        Sponsor.Organization.Tier.Gold -> {
            Image(
                painterResource(Res.drawable.svg_sponsor_tier_gold),
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
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        color = KnightsTheme.colorScheme.lightSurface,
    ) {
        Row(
            modifier = Modifier
                .basicMarquee()
                .padding(8.dp, 11.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            individualSponsors.forEach {
                Text(
                    text = it.name,
                    style = KnightsTheme.typography.labelSmallM,
                    color = Blue01,
                    modifier = Modifier,
                )
            }
        }
    }
}

private val organizationSponsors = listOf(
    Sponsor.Organization(
        tier = Sponsor.Organization.Tier.Platinum,
        name = "RevenueCat",
        logoRes = Res.drawable.sponsor_logo_revenue_cat,
        url = "https://www.revenuecat.com",
    ),
    Sponsor.Organization(
        tier = Sponsor.Organization.Tier.Gold,
        name = "JetBrains",
        logoRes = Res.drawable.sponsor_logo_jetbrains,
        url = "https://www.jetbrains.com/",
    ),
)

private val individualSponsors = listOf(
    Sponsor.Individual(name = "경창현"),
    Sponsor.Individual(name = "김태우"),
    Sponsor.Individual(name = "박덕성"),
    Sponsor.Individual(name = "성희영"),
    Sponsor.Individual(name = "이재일"),
    Sponsor.Individual(name = "이현민"),
    Sponsor.Individual(name = "임태우"),
    Sponsor.Individual(name = "장보미"),
    Sponsor.Individual(name = "정태훈"),
    Sponsor.Individual(name = "정현아"),
    Sponsor.Individual(name = "최익환"),
    Sponsor.Individual(name = "황창훈"),
)

@Preview
@Composable
fun HomeSponsorCardLightPreview() {
    KnightsTheme(darkTheme = false) {
        HomeSponsorCard(
            modifier = Modifier.fillMaxWidth(),
            onOrganizationSponsorClick = {},
        )
    }
}

@Preview
@Composable
fun HomeSponsorCardDarkPreview() {
    KnightsTheme(darkTheme = true) {
        HomeSponsorCard(
            modifier = Modifier.fillMaxWidth(),
            onOrganizationSponsorClick = {},
        )
    }
}
