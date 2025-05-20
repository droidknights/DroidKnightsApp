package com.droidknights.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.data.setting.di.coreDataSettingModule
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.main.MainScreen
import com.droidknights.app.feature.setting.di.featureSettingModule
import droidknights.composeapp.generated.resources.NotoSans
import droidknights.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Composable
internal fun App(
    onDarkThemeChange: ((Boolean) -> Unit)? = null,
    fontFamily: FontFamily = FontFamily(Font(resource = Res.font.NotoSans)),
) {
    KoinApplication(
        application = koinAppDeclaration,
    ) {
        val appViewModel = koinViewModel<AppViewModel>()
        val appUiState by appViewModel.uiState.collectAsStateWithLifecycle()

        if (onDarkThemeChange != null) {
            LaunchedEffect(appUiState.isDarkTheme) { onDarkThemeChange(appUiState.isDarkTheme) }
        }

        KnightsTheme(
            darkTheme = appUiState.isDarkTheme,
            fontFamily = fontFamily,
        ) {
            MainScreen()
        }
    }
}

private val koinAppDeclaration: KoinAppDeclaration = {
    val appModule = module {
        viewModelOf(::AppViewModel)
    }
    val coreDataModules = listOf(
        coreDataSettingModule,
    )
    val featureViewModelModules = listOf(
        featureSettingModule,
    )
    modules(appModule)
    modules(coreDataModules)
    modules(featureViewModelModules)
}
