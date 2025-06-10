package com.droidknights.app.feature.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.navigation.MainTabRoute.Bookmark
import com.droidknights.app.core.navigation.MainTabRoute.Home
import com.droidknights.app.core.navigation.MainTabRoute.Setting
import com.droidknights.app.core.router.api.model.Route
import com.droidknights.app.feature.setting.component.LightDarkThemeCard
import com.droidknights.app.feature.setting.component.OpenSourceCard

@Composable
internal fun SettingScreen(
    padding: PaddingValues,
    settingViewModel: SettingViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(settingViewModel, lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            settingViewModel.loadAction()
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
    viewModel: SettingViewModel = hiltViewModel(),
    selectedTabRoute: State<Route> = remember { mutableStateOf(Setting) },
) {
    LaunchedEffect(selectedTabRoute.value) {
        when (selectedTabRoute.value) {
            Home -> viewModel.navigateHome()
            Bookmark -> viewModel.navigateBookmark()
            Setting -> Unit
        }
    }

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
