package com.droidknights.app2023.feature.setting

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.LocalDarkTheme
import com.droidknights.app2023.feature.setting.opensource.OpenSourceCard

@Composable
internal fun SettingScreen(
    padding: PaddingValues,
    onChangeDarkTheme: (Boolean) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .verticalScroll(scrollState)
            .padding(padding)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OpenSourceCard(
            context = LocalContext.current,
        )
        LightDarkThemeCard(
            onChangeDarkTheme = onChangeDarkTheme
        )
    }
}

@Composable
private fun LightDarkThemeCard(
    onChangeDarkTheme: (Boolean) -> Unit,
    darkTheme: Boolean = LocalDarkTheme.current
) {
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer) {
        KnightsCard {
            Column {
                Text(
                    text = stringResource(id = R.string.setting),
                    style = KnightsTheme.typography.headlineSmallBL,
                    modifier = Modifier.padding(top = 24.dp, start = 24.dp),
                )
                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val cardModifier = Modifier.weight(1f)
                    ThemeCard(
                        selected = !darkTheme,
                        titleRes = R.string.light_mode,
                        imageRes = R.drawable.img_light_mode,
                        onClick = { onChangeDarkTheme(false) },
                        modifier = cardModifier,
                    )
                    ThemeCard(
                        selected = darkTheme,
                        titleRes = R.string.dark_mode,
                        imageRes = R.drawable.img_dark_mode,
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
    selected: Boolean,
    @StringRes titleRes: Int,
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
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
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f)
            )
        }

        Text(
            text = stringResource(id = titleRes),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
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
private fun SettingScreenPreview() {
    KnightsTheme {
        SettingScreen(PaddingValues(0.dp)) { }
    }
}
