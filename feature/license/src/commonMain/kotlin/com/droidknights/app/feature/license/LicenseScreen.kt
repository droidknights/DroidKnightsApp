package com.droidknights.app.feature.license


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.droidknights.app.core.designsystem.components.Surface
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.m3.libraryColors
import com.mikepenz.aboutlibraries.ui.compose.rememberLibraries
import droidknights.feature.license.generated.resources.Res

import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun LicenseScreen(
    modifier: Modifier = Modifier,
    viewModel: LicenseViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LicenseScreen(uiState.isDarkTheme)

}

@Composable
fun LicenseScreen(isDarkTheme: Boolean) {


    val libraries by rememberLibraries {
        Res.readBytes("files/aboutlibraries.json").decodeToString()
    }
    val colors = if (isDarkTheme) {
        LibraryDefaults.libraryColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        )
    } else {
        LibraryDefaults.libraryColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    }
    Surface(modifier = Modifier.systemBarsPadding()) {
        LibrariesContainer(libraries = libraries, modifier = Modifier.fillMaxSize(), colors =  colors)

    }

}
