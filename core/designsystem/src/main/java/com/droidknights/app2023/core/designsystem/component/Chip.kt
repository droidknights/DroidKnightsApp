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
        ProvideTextStyle(MaterialTheme.typography.labelMedium) {
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
                containerColor = Color(0xFF5D5D5D),
                labelColor = Color(0xFFDCDCDC),
            )
            TextChip(
                "Track 02",
                containerColor = Color(0xFFA1ED00),
                labelColor = Color(0xFF386524),
            )
            TextChip(
                "16:45 발표",
                containerColor = Color(0xFFD9F899),
                labelColor = Color(0xFF386524),
            )
        }
    }
}
