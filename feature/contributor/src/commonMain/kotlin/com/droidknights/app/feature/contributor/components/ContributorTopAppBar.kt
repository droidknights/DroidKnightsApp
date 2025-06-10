package com.droidknights.app.feature.contributor.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.IconButton
import com.droidknights.app.core.designsystem.components.TopAppBar
import droidknights.core.designsystem.generated.resources.DesignRes
import droidknights.core.designsystem.generated.resources.ic_close
import droidknights.feature.contributor.generated.resources.Res
import droidknights.feature.contributor.generated.resources.contributor_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ContributorTopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = stringResource(Res.string.contributor_title),
        actions = {
            IconButton(
                onClick = onBackClick,
                modifier = it,
            ) {
                Icon(
                    painter = painterResource(DesignRes.drawable.ic_close),
                    contentDescription = null,
                )
            }
        },
        modifier = modifier,
    )
}
