package com.droidknights.app2023.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.theme.DarkGray
import com.droidknights.app2023.core.designsystem.theme.Green01
import com.droidknights.app2023.core.designsystem.theme.Green04
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.LightGray
import com.droidknights.app2023.core.designsystem.theme.Yellow03A40
import com.droidknights.app2023.core.designsystem.theme.Yellow04

@Composable
fun TextChip(
    text: String,
    containerColor: Color,
    labelColor: Color,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = containerColor,
    ) {
        ProvideTextStyle(KnightsTheme.typography.labelMediumR) {
            Text(text = text, color = labelColor, modifier = Modifier.padding(TextChipPadding))
        }
    }
}

private val TextChipPadding = PaddingValues(12.dp, 2.dp, 12.dp, 2.dp)

@Preview
@Composable
private fun TextChipPreview() {
    MaterialTheme {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            TextChip(
                "카테고리",
                containerColor = DarkGray,
                labelColor = LightGray,
            )
            TextChip(
                "Track 02",
                containerColor = Green01,
                labelColor = Green04,
            )
            TextChip(
                "16:45 발표",
                containerColor = Yellow03A40,
                labelColor = Yellow04,
            )
        }
    }
}
