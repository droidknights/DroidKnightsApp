package com.droidknights.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.data.session.di.coreDataSessionModule
import com.droidknights.app.core.data.setting.di.coreDataSettingModule
import com.droidknights.app.core.datastore.core.di.coreDatastoreCoreModules
import com.droidknights.app.core.datastore.session.di.coreDatastoreSessionModule
import com.droidknights.app.core.datastore.settings.di.coreDatastoreSettingsModule
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.domain.session.di.coreDomainSessionModule
import com.droidknights.app.core.network.di.coreNetworkModule
import com.droidknights.app.feature.contributor.di.featureContributorModule
import com.droidknights.app.feature.main.MainScreen
import com.droidknights.app.feature.session.di.featureSessionModule
import com.droidknights.app.feature.setting.di.featureSettingModule
import droidknights.composeapp.generated.resources.NotoSans
import droidknights.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@Composable
internal fun App(
    appViewModel: AppViewModel = koinViewModel(),
    onDarkThemeChange: ((Boolean) -> Unit)? = null,
    fontFamily: FontFamily = FontFamily(Font(resource = Res.font.NotoSans)),
) {
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

internal val appModule = module {
    // :core:data
    includes(
        coreDataSettingModule,
        coreDataSessionModule,
    )
    // :core:datastore
    includes(coreDatastoreCoreModules)
    includes(
        coreDatastoreSessionModule,
        coreDatastoreSettingsModule,
    )
    // :core:domain
    includes(
        coreDomainSessionModule,
    )
    // :feature
    includes(
    val coreNetworkModules = listOf(
        coreNetworkModule,
    )
    val featureModules = listOf(
        featureSessionModule,
        featureSettingModule,
        featureContributorModule,
    )

    viewModelOf(::AppViewModel)
}

internal fun knightsAppDeclaration(
    additionalDeclaration: KoinApplication.() -> Unit = {},
): KoinAppDeclaration = {
    modules(appModule)
    additionalDeclaration()
}
