package com.droidknights.app.feature.bookmark.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.Graphite
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.Purple01
import droidknights.feature.bookmark.generated.resources.Res
import droidknights.feature.bookmark.generated.resources.ic_trash
import droidknights.feature.bookmark.generated.resources.remove_from_bookmark
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun RemoveBookmarkSnackBar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .background(Graphite),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp).size(24.dp),
            painter = painterResource(Res.drawable.ic_trash),
            contentDescription = null,
            tint = Purple01,
        )
        Text(
            text = stringResource(Res.string.remove_from_bookmark),
            style = KnightsTheme.typography.bodyMediumR,
            color = Purple01,
        )
    }
}

@Preview
@Composable
private fun BookmarkStatePopupPreview() {
    KnightsTheme {
        RemoveBookmarkSnackBar(onClick = {})
    }
}
