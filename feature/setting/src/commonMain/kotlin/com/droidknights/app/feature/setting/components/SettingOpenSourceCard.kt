package com.droidknights.app.feature.setting.components

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
import droidknights.feature.setting.generated.resources.Res
import droidknights.feature.setting.generated.resources.ic_arrow_right
import droidknights.feature.setting.generated.resources.setting_open_source_card_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingOpenSourceCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        color = KnightsTheme.colorScheme.darkSurface,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(64.dp),
        ) {
            Text(
                text = stringResource(Res.string.setting_open_source_card_title),
                style = KnightsTheme.typography.headlineSmallBL,
            )

            Icon(
                painter = painterResource(Res.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End),
                tint = Blue01,
            )
        }
    }
}

@Preview
@Composable
fun SettingOpenSourceCardPreview() {
    KnightsTheme {
        SettingOpenSourceCard(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
