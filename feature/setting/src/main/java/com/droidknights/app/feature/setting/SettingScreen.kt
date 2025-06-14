package com.droidknights.app.feature.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.setting.component.LightDarkThemeCard
import com.droidknights.app.feature.setting.component.OpenSourceCard
import kotlinx.coroutines.flow.launchIn

@Composable
internal fun SettingScreen(
    padding: PaddingValues,
    settingViewModel: SettingViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(settingViewModel, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            settingViewModel.loadAction().launchIn(this)
        }
    }
    SettingScreen(
        padding = padding,
        onChangeDarkTheme = {
            settingViewModel.send(SettingAction.ChangeDarkTheme(it))
        },
    )
}

@Composable
private fun SettingScreen(
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
            onChangeDarkTheme = onChangeDarkTheme,
        )
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    KnightsTheme {
        SettingScreen(
            padding = PaddingValues(0.dp),
            onChangeDarkTheme = {},
        )
    }
}
