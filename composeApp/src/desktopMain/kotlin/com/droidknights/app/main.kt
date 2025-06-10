@file:Suppress("Filename")

package com.droidknights.app

import androidx.compose.ui.window.singleWindowApplication
import org.koin.compose.KoinApplication

val knightsAppDeclaration = knightsAppDeclaration()

fun main() = singleWindowApplication(
    title = "DroidKnights",
) {
    KoinApplication(
        application = knightsAppDeclaration,
    ) {
        App()
    }
}
