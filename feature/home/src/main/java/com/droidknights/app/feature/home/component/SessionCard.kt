package com.droidknights.app.feature.home.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.home.R

@Composable
internal fun SessionCard(
    onClick: () -> Unit,
) {
    KnightsCard(
        onClick = onClick,
        color = KnightsColor.Blue02,
        modifier = Modifier
            .defaultMinSize(minHeight = 148.dp)
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.background_home_session_card),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 18.dp, bottom = 38.dp)
            ) {
                Text(
                    text = stringResource(R.string.session_card_caption),
                    style = KnightsTheme.typography.titleSmallM140,
                    color = KnightsColor.White.copy(alpha = 0.6f),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = stringResource(R.string.session_card_title_one),
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = KnightsColor.White,
                )

                Row {
                    Text(
                        text = stringResource(R.string.session_card_title_two),
                        style = KnightsTheme.typography.headlineSmallBL,
                        color = KnightsColor.White,
                        modifier = Modifier
                            .padding(end = 4.dp)
                    )

                    Image(
                        painter = painterResource(R.drawable.img_session_intro),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SessionCardPreview() {
    KnightsTheme {
        SessionCard(
            onClick = { }
        )
    }
}
