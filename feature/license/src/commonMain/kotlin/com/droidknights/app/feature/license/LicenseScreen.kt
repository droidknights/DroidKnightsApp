package com.droidknights.app.feature.license

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.m3.libraryColors
import com.mikepenz.aboutlibraries.ui.compose.rememberLibraries
import droidknights.feature.license.generated.resources.Res

@Composable
internal fun LicenseScreen(
    modifier: Modifier = Modifier,
) {
    val libraries by rememberLibraries {
        Res.readBytes("files/aboutlibraries.json").decodeToString()
    }
    val colors = LibraryDefaults.libraryColors(
        backgroundColor = KnightsTheme.colorScheme.background,
        contentColor = KnightsTheme.colorScheme.onBackground,
    )
    Surface(modifier = modifier.systemBarsPadding()) {
        LibrariesContainer(
            libraries = libraries,
            modifier = Modifier.fillMaxSize(),
            colors = colors,
        )
    }
}
