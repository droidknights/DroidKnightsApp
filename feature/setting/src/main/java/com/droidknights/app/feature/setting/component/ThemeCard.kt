package com.droidknights.app.feature.setting.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalDarkTheme
import com.droidknights.app.feature.setting.R

@Composable
internal fun LightDarkThemeCard(
    modifier: Modifier = Modifier,
    onChangeDarkTheme: (Boolean) -> Unit,
    darkTheme: Boolean = LocalDarkTheme.current,
    @StringRes lightModeTitleRes: Int = R.string.light_mode,
    @StringRes darkModeTitleRes: Int = R.string.dark_mode,
    @DrawableRes lightModeImageRes: Int = R.drawable.img_light_mode,
    @DrawableRes darkModeImageRes: Int = R.drawable.img_dark_mode,
) {
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer) {
        KnightsCard(modifier = modifier) {
            Column {
                Text(
                    text = stringResource(id = R.string.setting),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = KnightsTheme.typography.headlineSmallBL,
                    modifier = Modifier.padding(top = 24.dp, start = 24.dp),
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val cardModifier = Modifier.weight(1f)
                    ThemeCard(
                        selected = darkTheme.not(),
                        titleRes = lightModeTitleRes,
                        imageRes = lightModeImageRes,
                        onClick = { onChangeDarkTheme(false) },
                        modifier = cardModifier,
                    )
                    ThemeCard(
                        selected = darkTheme,
                        titleRes = darkModeTitleRes,
                        imageRes = darkModeImageRes,
                        onClick = { onChangeDarkTheme(true) },
                        modifier = cardModifier,
                    )
                }
            }
        }
    }
}

@Composable
private fun ThemeCard(
    modifier: Modifier = Modifier,
    selected: Boolean,
    @StringRes titleRes: Int,
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            onClick = onClick,
        ) {
            Image(
                painter = painterResource(id = imageRes), contentDescription = null, modifier = Modifier.aspectRatio(1f)
            )
        }

        Text(
            text = stringResource(id = titleRes),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = KnightsTheme.typography.titleSmallM140,
        )

        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unselectedColor = MaterialTheme.colorScheme.onSurface,
            )
        )
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

@Preview
@Composable
private fun LightModeThemeCardPreview() {
    KnightsTheme {
        ThemeCard(
            selected = false,
            titleRes = R.string.light_mode,
            imageRes = R.drawable.img_light_mode,
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
            imageRes = R.drawable.img_dark_mode,
            onClick = { },
        )
    }
}
