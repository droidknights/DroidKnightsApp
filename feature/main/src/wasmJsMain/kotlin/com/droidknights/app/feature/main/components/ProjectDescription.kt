package com.droidknights.app.feature.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalContentColor
import com.droidknights.app.feature.main.model.Contributor
import com.droidknights.app.feature.main.model.ProjectBranch
import com.droidknights.app.feature.main.model.branches2025
import com.droidknights.app.feature.main.model.contributors2023
import com.droidknights.app.feature.main.model.contributors2024

@Composable
internal fun ProjectDescription(
    onProjectBranchClick: (ProjectBranch) -> Unit,
    onContributorClick: (Contributor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "드로이드나이츠 앱의\n컨트리뷰터가 되어주세요!",
            style = KnightsTheme.typography.displayMediumEB,
        )

        Spacer(modifier = Modifier.height(8.dp))

        ProjectBranchButtons(
            onClick = onProjectBranchClick,
            branches = branches2025,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Contributors(
            title = "2024",
            contributors = contributors2024,
            onClick = onContributorClick,
        )

        Contributors(
            title = "2023",
            contributors = contributors2023,
            onClick = onContributorClick,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "* 이 페이지는 Compose Multiplatform으로 만들어졌습니다. 웹에서 드로이드나이츠 앱을 미리 체험 해보세요!",
            style = KnightsTheme.typography.bodyLargeR,
            color = LocalContentColor.current.copy(alpha = 0.5F),
        )
    }
}
