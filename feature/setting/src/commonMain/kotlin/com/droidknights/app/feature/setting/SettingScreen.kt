package com.droidknights.app.feature.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.feature.setting.components.SettingDarkThemeCard
import com.droidknights.app.feature.setting.components.SettingOpenSourceCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingScreen(
        isDarkTheme = uiState.isDarkTheme,
        onDarkThemeChange = viewModel::updateDarkTheme,
        modifier = modifier,
    )
}

@Composable
private fun SettingScreen(
    isDarkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp)
            .padding(bottom = 64.dp), // MainBottomBar 56(높이) + 8(하단 패딩)
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SettingOpenSourceCard(
            onClick = {
                // TODO 오픈소스 라이브러리 목록 보여주기
            },
            modifier = Modifier.fillMaxWidth(),
        )
        SettingDarkThemeCard(
            isDarkTheme = isDarkTheme,
            onDarkThemeChange = onDarkThemeChange,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
