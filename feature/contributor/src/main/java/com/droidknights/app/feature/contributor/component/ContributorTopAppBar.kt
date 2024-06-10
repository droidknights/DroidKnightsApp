package com.droidknights.app.feature.contributor.component

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsTopAppBar
import com.droidknights.app.core.designsystem.component.TopAppBarNavigationType
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.contributor.R

@Composable
internal fun ContributorTopAppBar(
    isAtTop: Boolean,
    onBackClick: () -> Unit,
) {
    val alpha by animateFloatAsState(
        targetValue = if (isAtTop) 0f else 0.8f,
        label = "topAppBarContainerColor",
    )
    val containerColor = MaterialTheme.colorScheme.surfaceDim.copy(alpha)
    KnightsTopAppBar(
        titleRes = R.string.contributor_top_title,
        navigationIconContentDescription = null,
        modifier = Modifier.statusBarsPadding(),
        navigationType = TopAppBarNavigationType.Close,
        onNavigationClick = onBackClick,
        containerColor = containerColor,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContributorTopAppBarPreview() {
    KnightsTheme {
        Column {
            ContributorTopAppBar(
                isAtTop = false,
                onBackClick = {},
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            ContributorTopAppBar(
                isAtTop = true,
                onBackClick = {},
            )
        }
    }
}
