package com.droidknights.app.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalContentColor
import droidknights.feature.home.generated.resources.Res
import droidknights.feature.home.generated.resources.background_home_session_card
import droidknights.feature.home.generated.resources.home_session_card_desc
import droidknights.feature.home.generated.resources.home_session_card_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeSessionCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        color = KnightsTheme.colorScheme.primary,
        shape = RoundedCornerShape(16.dp),
    ) {
        Box {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = painterResource(Res.drawable.background_home_session_card),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 18.dp, 24.dp, 38.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(Res.string.home_session_card_title),
                    style = KnightsTheme.typography.titleSmallM140,
                    color = LocalContentColor.current.copy(alpha = 0.6F),
                )
                Text(
                    text = stringResource(Res.string.home_session_card_desc),
                    style = KnightsTheme.typography.headlineSmallBL,
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeSessionCardPreview() {
    KnightsTheme {
        HomeSessionCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}
