package com.droidknights.app.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import droidknights.feature.home.generated.resources.Res
import droidknights.feature.home.generated.resources.home_contributor_card_desc
import droidknights.feature.home.generated.resources.home_contributor_card_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeContributorCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        contentColor = KnightsTheme.colorScheme.primary,
        shape = RoundedCornerShape(16.dp),
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(24.dp, 24.dp, 36.dp, 24.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = stringResource(Res.string.home_contributor_card_title),
                    style = KnightsTheme.typography.headlineSmallBL,
                )
                Text(
                    text = stringResource(Res.string.home_contributor_card_desc),
                    style = KnightsTheme.typography.titleSmallM140,
                )
            }
            // TODO 컨트리뷰터 아이콘
        }
    }
}

@Preview
@Composable
fun HomeContributorCardPreview() {
    KnightsTheme {
        HomeContributorCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
        )
    }
}
