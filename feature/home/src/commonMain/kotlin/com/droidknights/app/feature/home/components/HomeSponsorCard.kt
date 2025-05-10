package com.droidknights.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.home.generated.resources.Res
import droidknights.feature.home.generated.resources.background_home_sponsor_card
import droidknights.feature.home.generated.resources.home_sponsor_card_desc
import droidknights.feature.home.generated.resources.home_sponsor_card_title
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp), // TODO 스폰서 추가 후 삭제
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Res.drawable.background_home_sponsor_card),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Column(
                modifier = Modifier
                    .padding(24.dp, 24.dp, 36.dp, 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(Res.string.home_sponsor_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                )
                Text(
                    text = stringResource(Res.string.home_sponsor_card_desc),
                    style = KnightsTheme.typography.titleSmallM140,
                )
            }
            // TODO 스폰서 아이콘
        }
    }
}

@Preview
@Composable
fun HomeSponsorCardPreview() {
    KnightsTheme {
        HomeSponsorCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
