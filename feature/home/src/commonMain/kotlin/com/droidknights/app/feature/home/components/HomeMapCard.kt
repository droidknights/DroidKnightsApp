package com.droidknights.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.home.generated.resources.Res
import droidknights.feature.home.generated.resources.background_home_map_card
import droidknights.feature.home.generated.resources.home_map_card_desc
import droidknights.feature.home.generated.resources.home_map_card_title
import droidknights.feature.home.generated.resources.ic_location
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeMapCard(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = KnightsTheme.colorScheme.primary,
        shape = RoundedCornerShape(16.dp),
    ) {
        Box {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painterResource(Res.drawable.background_home_map_card),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp, bottom = 56.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(Res.drawable.ic_location),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(Res.string.home_map_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                )
                Text(
                    text = stringResource(Res.string.home_map_card_desc),
                    style = KnightsTheme.typography.titleSmallM140,
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeMapCardPreview() {
    KnightsTheme {
        HomeMapCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
