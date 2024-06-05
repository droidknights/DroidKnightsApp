package com.droidknights.app.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun IconToggleButton(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    @DrawableRes checkedImageRes: Int,
    @DrawableRes uncheckedImageRes: Int
) {
    IconToggleButton(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = onCheckedChange,
    ) {
        Icon(
            painter = if (isChecked) {
                painterResource(id = checkedImageRes)
            } else {
                painterResource(id = uncheckedImageRes)
            },
            contentDescription = null
        )
    }
}
