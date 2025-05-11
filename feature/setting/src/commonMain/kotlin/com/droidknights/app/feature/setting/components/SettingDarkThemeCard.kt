package com.droidknights.app.feature.setting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalDarkTheme
import droidknights.feature.setting.generated.resources.Res
import droidknights.feature.setting.generated.resources.ic_disabled
import droidknights.feature.setting.generated.resources.ic_enabled
import droidknights.feature.setting.generated.resources.img_android
import droidknights.feature.setting.generated.resources.setting_dark_theme_card_desc_dark_mode
import droidknights.feature.setting.generated.resources.setting_dark_theme_card_desc_light_mode
import droidknights.feature.setting.generated.resources.setting_dark_theme_card_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingDarkThemeCard(
    onDarkThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val darkTheme = LocalDarkTheme.current

    Surface(
        modifier = modifier,
        color = KnightsTheme.colorScheme.surface,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 24.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Text(
                text = stringResource(Res.string.setting_dark_theme_card_title),
                style = KnightsTheme.typography.headlineSmallBL,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ModeButton(
                    mode = Mode.Light,
                    isSelected = !darkTheme,
                    onClick = {
                        onDarkThemeChange(false)
                    },
                )
                ModeButton(
                    mode = Mode.Dark,
                    isSelected = darkTheme,
                    onClick = {
                        onDarkThemeChange(true)
                    },
                )
            }
        }
    }
}

@Composable
private fun RowScope.ModeButton(
    mode: Mode,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.weight(1F),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(1F)
                    .border(
                        width = 1.dp,
                        color = when (mode) {
                            Mode.Dark -> KnightsTheme.colorScheme.lightSurface
                            Mode.Light -> KnightsTheme.colorScheme.darkSurface
                        },
                        shape = RoundedCornerShape(12.dp),
                    )
                    .background(
                        color = when (mode) {
                            Mode.Dark -> KnightsTheme.colorScheme.darkSurface
                            Mode.Light -> KnightsTheme.colorScheme.lightSurface
                        },
                        shape = RoundedCornerShape(12.dp),
                    ),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Image(
                    painter = painterResource(Res.drawable.img_android),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = when (mode) {
                    Mode.Dark -> stringResource(Res.string.setting_dark_theme_card_desc_dark_mode)
                    Mode.Light -> stringResource(Res.string.setting_dark_theme_card_desc_light_mode)
                },
                style = KnightsTheme.typography.titleSmallM140,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painter = painterResource(
                    if (isSelected) {
                        Res.drawable.ic_enabled
                    } else {
                        Res.drawable.ic_disabled
                    },
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp),
            )
        }
    }
}

private enum class Mode {
    Dark, Light
}

@Preview
@Composable
fun SettingDarkThemeCardPreview() {
    var isDarkTheme by remember { mutableStateOf(false) }

    KnightsTheme(darkTheme = isDarkTheme) {
        SettingDarkThemeCard(
            onDarkThemeChange = { darkTheme ->
                isDarkTheme = darkTheme
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
