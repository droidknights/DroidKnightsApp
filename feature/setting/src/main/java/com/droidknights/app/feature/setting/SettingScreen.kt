package com.droidknights.app.feature.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.setting.component.LightDarkThemeCard
import com.droidknights.app.feature.setting.component.OpenSourceCard

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
        OpenSourceCard()
        LightDarkThemeCard(
            onChangeDarkTheme = onChangeDarkTheme
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
