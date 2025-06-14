package com.droidknights.app.feature.setting.component

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalDarkTheme
import com.droidknights.app.feature.setting.R

@Composable
internal fun LightDarkThemeCard(
    onChangeDarkTheme: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = LocalDarkTheme.current,
) {
    KnightsCard(
        modifier = modifier
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.setting),
                style = KnightsTheme.typography.headlineSmallBL,
                modifier = Modifier
                    .padding(top = 24.dp, start = 24.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 24.dp)
            ) {
                ThemeCard(
                    selected = darkTheme.not(),
                    titleRes = R.string.light_mode,
                    themeCardColor = KnightsColor.White,
                    onClick = { onChangeDarkTheme(false) },
                    modifier = Modifier
                        .weight(1f)
                )
                ThemeCard(
                    selected = darkTheme,
                    titleRes = R.string.dark_mode,
                    themeCardColor = KnightsColor.Graphite,
                    onClick = { onChangeDarkTheme(true) },
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun LightDarkThemeCardPreview() {
    KnightsTheme {
        LightDarkThemeCard(
            onChangeDarkTheme = { },
        )
    }
}

@Composable
private fun ThemeCard(
    selected: Boolean,
    @StringRes titleRes: Int,
    onClick: () -> Unit,
    themeCardColor: Color,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Surface(
        onClick = onClick,
        color = color,
        contentColor = contentColor,
        shape = KnightsTheme.shape.rounded12,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                shape = KnightsTheme.shape.rounded12,
                color = themeCardColor,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface).takeIf { selected },
                modifier = Modifier
                    .aspectRatio(1f)
            ) {
                Box(
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_android),
                        contentDescription = stringResource(titleRes),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            Text(
                text = stringResource(id = titleRes),
                style = KnightsTheme.typography.titleSmallM140,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
            )

            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.onSurface,
                    unselectedColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            )
        }
    }
}

@Preview
@Composable
private fun LightModeThemeCardPreview() {
    KnightsTheme {
        ThemeCard(
            selected = true,
            titleRes = R.string.light_mode,
            themeCardColor = KnightsColor.White,
            onClick = { },
        )
    }
}

@Preview
@Composable
private fun DarkModeThemeCardPreview() {
    KnightsTheme {
        ThemeCard(
            selected = true,
            titleRes = R.string.dark_mode,
            themeCardColor = KnightsColor.Graphite,
            onClick = { },
        )
    }
}
