package com.droidknights.app2023.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme

@Composable
internal fun SessionCard(
    onClick: () -> Unit,
) {
    KnightsCard(
        onClick = onClick,
        modifier = Modifier
            .height(164.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_card_session),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            SessionCardCaption()
            Text(
                text = stringResource(id = R.string.session_card_title),
                style = KnightsTheme.typography.headlineSmallBL,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}

@Composable
private fun SessionCardCaption() {
    Box(
        modifier = Modifier
            .background(Color(0xFF292929), RoundedCornerShape(32.dp))
            .padding(horizontal = 12.dp, vertical = 2.dp)
    ) {
        Text(
            text = stringResource(id = R.string.session_card_caption),
            style = KnightsTheme.typography.labelSmallM,
            color = Color(0xFFFFFFFF),
        )
    }
}

@Preview
@Composable
private fun SessionCardPreview() {
    KnightsTheme {
        SessionCard(
            onClick = { }
        )
    }
}
