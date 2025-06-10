package com.droidknights.app.feature.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsColorScheme
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalColorScheme
import com.droidknights.app.feature.map.components.MapTopAppBar
import droidknights.feature.map.generated.resources.Res
import droidknights.feature.map.generated.resources.map_title
import droidknights.feature.map.generated.resources.svg_map_dark_mode
import droidknights.feature.map.generated.resources.svg_map_light_mode
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MapScreen(
    onBackClick: () -> Unit,
) {
    val isDarkTheme = LocalColorScheme.current == KnightsColorScheme.darkColorScheme
    val mapImage = if (isDarkTheme) {
        Res.drawable.svg_map_dark_mode
    } else {
        Res.drawable.svg_map_light_mode
    }

    Surface(
        color = KnightsTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            MapTopAppBar(
                onBackClick = onBackClick,
            )
            Text(
                text = stringResource(Res.string.map_title),
                style = KnightsTheme.typography.headlineMediumB,
                modifier = Modifier.padding(
                    top = 8.dp,
                    start = 16.dp,
                ),
            )
            Image(
                modifier = Modifier.fillMaxWidth()
                    .padding(
                        top = 64.dp,
                        bottom = 64.dp,
                        start = 20.dp,
                        end = 14.dp,
                    ),
                painter = painterResource(mapImage),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
        }
    }
}

@Preview
@Composable
private fun MapScreenPreview() {
    KnightsTheme {
        MapScreen(
            onBackClick = { },
        )
    }
}
