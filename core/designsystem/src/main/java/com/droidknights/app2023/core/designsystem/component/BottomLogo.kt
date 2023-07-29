package com.droidknights.app2023.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.LightGray

@Composable
fun BottomLogo(color: Color = LightGray) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(BottomLogoHeight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Droid Knights 2023",
            style = KnightsTheme.typography.labelMediumR,
            color = color,
        )
    }
}

val BottomLogoHeight = 48.dp

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BottomLogoPreview() {
    KnightsTheme {
        BottomLogo()
    }
}
