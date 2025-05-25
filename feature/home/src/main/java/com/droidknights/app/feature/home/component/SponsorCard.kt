package com.droidknights.app.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.home.R
import com.droidknights.app.feature.home.SponsorsUiStatePreviewParameterProvider
import com.droidknights.app.feature.home.model.SponsorsUiState
import com.valentinilk.shimmer.shimmer

@Composable
internal fun SponsorCard(uiState: SponsorsUiState) {
    when (uiState) {
        SponsorsUiState.Empty -> Unit
        SponsorsUiState.Loading -> SponsorCardSkeleton()
        is SponsorsUiState.Sponsors -> SponsorCardContents()
    }
}

@Composable
private fun SponsorCardContents() {
    KnightsCard {
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
                Text(
                    text = stringResource(id = R.string.sponsor_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = KnightsColor.Blue01,
                )

                Text(
                    text = stringResource(R.string.sponsor_card_description),
                    style = KnightsTheme.typography.titleSmallM140,
                    color = KnightsColor.Blue01,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = painterResource(R.drawable.sponsor_logo_jetbrains),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = KnightsColor.White.copy(alpha = 0.5F),
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(60.dp),
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
        )
    }
}
