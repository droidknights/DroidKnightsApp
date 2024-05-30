package com.droidknights.app.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.DarkGray
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LightGray

@Composable
fun IconTextChip(
    text: String,
    containerColor: Color,
    labelColor: Color,
    iconPainter: Painter,
    iconTint: Color,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = containerColor,
    ) {
        Row(
            modifier = modifier.padding(start = 4.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = modifier
                    .width(20.dp)
                    .height(20.dp),
                painter = iconPainter,
                contentDescription = null,
                tint = iconTint
            )
            ProvideTextStyle(KnightsTheme.typography.labelSmallM) {
                Text(text = text, color = labelColor, modifier = Modifier.padding(top = 2.dp, bottom = 2.dp))
            }
        }
    }
}

@Preview
@Composable
private fun IconTextChipPreview() {
    MaterialTheme {
        IconTextChip(
            "북마크",
            containerColor = DarkGray,
            labelColor = LightGray,
            iconPainter = painterResource(id = androidx.appcompat.R.drawable.abc_btn_radio_material),
            iconTint = Color.Green
        )
    }
}
