package com.droidknights.app.feature.bookmark.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.bookmark.generated.resources.Res
import droidknights.feature.bookmark.generated.resources.book_mark_top_bar_title
import droidknights.feature.bookmark.generated.resources.edit_button_confirm_label
import droidknights.feature.bookmark.generated.resources.edit_button_edit_label
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun BookmarkTopAppBar(
    isEditMode: Boolean,
    onClickEditButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val editButtonColor by animateColorAsState(
        label = "Edit Button Color Animation",
        targetValue = if (isEditMode) {
            KnightsTheme.colorScheme.primary
        } else {
            KnightsTheme.colorScheme.editButtonColor
        },
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(Res.string.book_mark_top_bar_title),
            style = KnightsTheme.typography.titleSmallM,
            color = KnightsTheme.colorScheme.onSurface,
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = onClickEditButton)
                .padding(horizontal = 12.dp),
            text = if (isEditMode) {
                stringResource(Res.string.edit_button_confirm_label)
            } else {
                stringResource(Res.string.edit_button_edit_label)
            },
            style = KnightsTheme.typography.titleSmallM,
            color = editButtonColor,
        )
    }
}

@Preview
@Composable
private fun BookmarkTopAppBarLightPreview() {
    KnightsTheme(darkTheme = false) {
        BookmarkTopAppBar(
            modifier = Modifier.background(KnightsTheme.colorScheme.background),
            isEditMode = false,
            onClickEditButton = {},
        )
    }
}

@Preview
@Composable
private fun BookmarkTopAppBarDarkPreview() {
    KnightsTheme(darkTheme = true) {
        BookmarkTopAppBar(
            modifier = Modifier.background(KnightsTheme.colorScheme.background),
            isEditMode = false,
            onClickEditButton = {},
        )
    }
}

@Preview
@Composable
private fun BookmarkTopAppBarEditModeLightPreview() {
    KnightsTheme(darkTheme = false) {
        BookmarkTopAppBar(
            modifier = Modifier.background(KnightsTheme.colorScheme.background),
            isEditMode = true,
            onClickEditButton = {},
        )
    }
}

@Preview
@Composable
private fun BookmarkTopAppBarEditModeDarkPreview() {
    KnightsTheme(darkTheme = true) {
        BookmarkTopAppBar(
            modifier = Modifier.background(KnightsTheme.colorScheme.background),
            isEditMode = true,
            onClickEditButton = {},
        )
    }
}
