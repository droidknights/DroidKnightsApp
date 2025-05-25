package com.droidknights.app.feature.bookmark.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.bookmark.R

@Composable
internal fun BookmarkTopAppBar(
    isEditMode: Boolean,
    onClickEditButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val editButtonColor by animateColorAsState(
        label = "Edit Button Color Animation",
        targetValue = if (isEditMode) {
            KnightsColor.Purple01
        } else {
            KnightsColor.Gray
        }
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.book_mark_top_bar_title),
            style = KnightsTheme.typography.titleSmallM,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = onClickEditButton)
                .padding(horizontal = 12.dp),
            text = if (isEditMode) {
                stringResource(id = R.string.edit_button_confirm_label)
            } else {
                stringResource(id = R.string.edit_button_edit_label)
            },
            style = KnightsTheme.typography.titleSmallM,
            color = editButtonColor
        )
    }
}

@Preview
@Composable
private fun BookmarkTopAppBarPreview() {
    KnightsTheme {
        BookmarkTopAppBar(
            isEditMode = false,
            onClickEditButton = {}
        )
    }
}

@Preview
@Composable
private fun BookmarkTopAppBarEditModePreview() {
    KnightsTheme {
        BookmarkTopAppBar(
            isEditMode = true,
            onClickEditButton = {}
        )
    }
}
