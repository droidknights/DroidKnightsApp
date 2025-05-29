@file:Suppress("Filename")

package com.droidknights.app

import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.ComposeViewport
import droidknights.composeapp.generated.resources.NotoSans
import droidknights.composeapp.generated.resources.Res
import kotlinx.browser.document
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.preloadFont
import org.koin.compose.KoinApplication

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        KoinApplication(
            application = knightsAppDeclaration(),
        ) {
            val font by preloadFont(Res.font.NotoSans)
            font?.let { App(fontFamily = FontFamily(it)) }
        }
    }
}
