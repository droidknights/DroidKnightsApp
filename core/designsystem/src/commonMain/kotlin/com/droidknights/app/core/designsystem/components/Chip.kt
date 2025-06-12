package com.droidknights.app.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.Graphite
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalContentColor
import com.droidknights.app.core.designsystem.theme.White
import droidknights.core.designsystem.generated.resources.DesignRes
import droidknights.core.designsystem.generated.resources.ic_flagbookmark
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    style: ChipStyle = ChipStyle.Default,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = when (style) {
            ChipStyle.Default -> KnightsTheme.colorScheme.neutralSurface
            ChipStyle.Primary -> KnightsTheme.colorScheme.primarySurface
            ChipStyle.Secondary -> KnightsTheme.colorScheme.secondarySurface
            ChipStyle.Accent -> KnightsTheme.colorScheme.accentSurface
            ChipStyle.Border -> Color.Transparent
        },
        border = if (style == ChipStyle.Border) {
            BorderStroke(
                width = 1.dp,
                color = LocalContentColor.current.copy(alpha = 0.5F),
            )
        } else {
            null
        },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(ChipPadding),
        ) {
            if (icon != null) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(8.dp),
                )
            }
            Text(
                text = text,
                style = KnightsTheme.typography.labelSmallM,
                maxLines = 1,
                color = when (style) {
                    ChipStyle.Default,
                    ChipStyle.Primary,
                    ChipStyle.Secondary,
                    ChipStyle.Accent,
                    -> LocalContentColor.current
                    ChipStyle.Border -> LocalContentColor.current.copy(alpha = 0.5F)
                },
            )
        }
    }
}

enum class ChipStyle {
    Default, Primary, Secondary, Accent, Border
}

private val ChipPadding = PaddingValues(12.dp, 2.dp, 12.dp, 2.dp)

@Preview
@Composable
private fun ChipPreview() {
    KnightsTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Surface(color = Graphite) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(16.dp),
                ) {
                    Chip("Android")
                    Chip(
                        text = "Track 02",
                        style = ChipStyle.Primary,
                    )
                    Chip(
                        text = "16:45 발표",
                        style = ChipStyle.Secondary,
                    )
                    Chip(
                        text = "북마크",
                        icon = painterResource(DesignRes.drawable.ic_flagbookmark),
                        style = ChipStyle.Accent,
                    )
                    Chip(
                        "카테고리",
                        style = ChipStyle.Border,
                    )
                }
            }
            Surface(color = White) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(16.dp),
                ) {
                    Chip("Android")
                    Chip(
                        text = "Track 02",
                        style = ChipStyle.Primary,
                    )
                    Chip(
                        text = "16:45 발표",
                        style = ChipStyle.Secondary,
                    )
                    Chip(
                        text = "북마크",
                        icon = painterResource(DesignRes.drawable.ic_flagbookmark),
                        style = ChipStyle.Accent,
                    )
                    Chip(
                        "카테고리",
                        style = ChipStyle.Border,
                    )
                }
            }
        }
    }
}
