package com.droidknights.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.shader.components.EllipticalGlowBackground
import droidknights.feature.home.generated.resources.Res
import droidknights.feature.home.generated.resources.home_sponsor_card_desc
import droidknights.feature.home.generated.resources.home_sponsor_card_title
import droidknights.feature.home.generated.resources.sponsor_logo_jetbrains
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeSponsorCard(
    modifier: Modifier = Modifier,
) {
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
                    text = stringResource(Res.string.home_sponsor_card_desc),
                    style = KnightsTheme.typography.titleSmallM140,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    painter = painterResource(Res.drawable.sponsor_logo_jetbrains),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .background(
                            color = KnightsTheme.colorScheme.lightSurface.copy(alpha = 0.5F),
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(60.dp),

                )
            }
        }
    }
}

@Preview
@Composable
fun HomeSponsorCardLightPreview() {
    KnightsTheme(darkTheme = false) {
        HomeSponsorCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun HomeSponsorCardDarkPreview() {
    KnightsTheme(darkTheme = true) {
        HomeSponsorCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
