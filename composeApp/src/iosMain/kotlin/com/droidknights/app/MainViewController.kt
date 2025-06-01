package com.droidknights.app

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.compose.KoinApplication

fun MainViewController() = ComposeUIViewController {
    KoinApplication(
        application = knightsAppDeclaration(),
    ) {
        App()
    }
}
