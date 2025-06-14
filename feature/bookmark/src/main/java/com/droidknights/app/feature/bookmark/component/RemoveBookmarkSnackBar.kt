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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.bookmark.R

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
            .background(KnightsColor.Graphite),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp).size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
            contentDescription = null,
            tint = KnightsColor.Purple01
        )
        Text(
            text = stringResource(id = R.string.remove_from_bookmark),
            style = KnightsTheme.typography.bodyMediumR,
            color = KnightsColor.Purple01
        )
    }
}

@Preview(backgroundColor = 0xFFFFFF)
@Composable
private fun BookmarkStatePopupPreview() {
    RemoveBookmarkSnackBar(onClick = {})
}
