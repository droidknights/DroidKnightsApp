package com.droidknights.app2023.feature.session

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.core.designsystem.theme.surfaceDim

internal enum class Track(
    @StringRes val titleRes: Int,
) {
    Track1(R.string.session_track_01),
    Track2(R.string.session_track_02),
    Track3(R.string.session_track_03),
}

@Composable
internal fun SessionTabRow(
    selectedTrack: Track,
    tracks: List<Track>,
    onTrackSelect: (Track) -> Unit,
    modifier: Modifier = Modifier,
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        mutableStateListOf<Dp>().apply { addAll(tracks.map { 0.dp }) }
    }
    val selectedTabIndex = selectedTrack.ordinal
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceDim,
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator = { tabPositions ->
            TabIndicator(
                height = 3.dp,
                modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                )
            )
        },
        divider = { Divider(color = MaterialTheme.colorScheme.outline) }
    ) {
        tracks.forEachIndexed { tabIndex, track ->
            Tab(
                track = track,
                selected = selectedTrack == track,
                onClick = { onTrackSelect(track) },
                onTextLayout = { textLayoutResult ->
                    tabWidths[tabIndex] = with(density) { textLayoutResult.size.width.toDp() }
                }
            )
        }
    }
}

@Composable
private fun TabIndicator(height: Dp, modifier: Modifier) {
    val brush = SolidColor(MaterialTheme.colorScheme.onSurface)
    Box(
        modifier = modifier
            .height(height * 2)
            .offset { IntOffset(0, height.roundToPx()) }
            .background(brush, RoundedCornerShape(8.dp))
    )
}

@Composable
private fun Tab(
    track: Track,
    selected: Boolean,
    onClick: () -> Unit,
    onTextLayout: (TextLayoutResult) -> Unit,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        text = {
            Text(
                text = stringResource(id = track.titleRes),
                style = KnightsTheme.typography.titleSmallM,
                onTextLayout = { textLayoutResult -> onTextLayout(textLayoutResult) }
            )
        }
    )
}

private fun Modifier.tabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp,
): Modifier = composed {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SessionTabIndicatorPreview() {
    KnightsTheme {
        SessionTabRow(
            selectedTrack = Track.Track2,
            tracks = Track.values().toList(),
            onTrackSelect = { },
            modifier = Modifier.size(320.dp, 48.dp),
        )
    }
}
