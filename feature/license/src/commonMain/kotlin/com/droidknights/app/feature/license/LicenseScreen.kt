package com.droidknights.app.feature.license

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.IconButton
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.TopAppBar
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.m3.libraryColors
import com.mikepenz.aboutlibraries.ui.compose.rememberLibraries
import droidknights.core.designsystem.generated.resources.DesignRes
import droidknights.core.designsystem.generated.resources.ic_arrow_back
import droidknights.feature.license.generated.resources.Res
import droidknights.feature.license.generated.resources.license_top_app_bar_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun LicenseScreen(
    onBackClick: () -> Unit,
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
        Column {
            TopAppBar(
                title = stringResource(Res.string.license_top_app_bar_title),
                navigation = {
                    IconButton(
                        onClick = { onBackClick() },
                        modifier = it,
                    ) {
                        Icon(
                            painter = painterResource(DesignRes.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                },
            )
            LibrariesContainer(
                libraries = libraries,
                modifier = Modifier.fillMaxSize(),
                colors = colors,
            )
        }
    }
}
