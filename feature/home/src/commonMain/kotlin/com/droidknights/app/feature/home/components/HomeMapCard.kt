package com.droidknights.app.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.Blue01
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.White
import com.droidknights.app.core.shader.components.LayeredShaderBackground
import droidknights.feature.home.generated.resources.Res
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
        color = Blue01,
        shape = RoundedCornerShape(16.dp),
    ) {
        LayeredShaderBackground {
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
                    tint = White,
                    contentDescription = null,
                )
                Text(
                    text = stringResource(Res.string.home_map_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = White
                )
                Text(
                    text = stringResource(Res.string.home_map_card_desc),
                    style = KnightsTheme.typography.titleSmallM140,
                    color = White
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeMapCardPreview() {
    KnightsTheme(darkTheme = true) {
        HomeMapCard(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
