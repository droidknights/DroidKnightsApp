package com.droidknights.app.feature.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.HorizontalDivider
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.main.model.Contributor
import com.droidknights.app.feature.main.model.ProjectBranch
import com.droidknights.app.feature.main.model.branches2025
import com.droidknights.app.feature.main.model.contributors2023
import com.droidknights.app.feature.main.model.contributors2024
import com.droidknights.app.feature.main.model.sponsors2025

@Composable
internal fun MobileProjectDescription(
    onBranchButtonsClick: (ProjectBranch) -> Unit,
    onContributorClick: (Contributor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(bottom = 28.dp),
    ) {
        MobileProjectBranchButtons(
            onClick = onBranchButtonsClick,
            branches = branches2025,
            modifier = Modifier.padding(vertical = 16.dp),
        )
        MobileContributors(
            title = "2025",
            contributors = contributors2024,
            onClick = onContributorClick,
        )
        Spacer(modifier = Modifier.height(24.dp))

        MobileSponsors(
            title = "후원자",
            sponsors = sponsors2025,
        )
        Spacer(modifier = Modifier.height(32.dp))

        HorizontalDivider(color = KnightsTheme.colorScheme.borderColor, thickness = 1.dp)
        Spacer(modifier = Modifier.height(40.dp))
        MobileContributors(
            title = "2024",
            contributors = contributors2024,
            onClick = onContributorClick,
        )
        Spacer(modifier = Modifier.height(24.dp))
        MobileContributors(
            title = "2023",
            contributors = contributors2023,
            onClick = onContributorClick,
        )
    }
}
