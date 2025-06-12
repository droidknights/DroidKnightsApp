package com.droidknights.app.feature.main.components

import Sponsors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.HorizontalDivider
import com.droidknights.app.feature.main.model.Contributor
import com.droidknights.app.feature.main.model.ProjectBranch
import com.droidknights.app.feature.main.model.branches2025
import com.droidknights.app.feature.main.model.contributors2024
import com.droidknights.app.feature.main.model.sponsors2025

@Composable
internal fun ProjectDescription(
    isMobile: Boolean,
    onContributorClick: (Contributor) -> Unit,
    modifier: Modifier = Modifier,
    onBranchButtonsClick: (ProjectBranch) -> Unit = {},
) {
    Column(
        modifier = if (isMobile) modifier.padding(bottom = 28.dp) else modifier.padding(vertical = 119.dp),
    ) {
        if (isMobile) {
            ProjectBranchButtons(
                isMobile = isMobile,
                onClick = onBranchButtonsClick,
                branches = branches2025,
                modifier = Modifier.padding(vertical = 16.dp),
            )
        }
        Contributors(
            title = "2025",
            isMobile = isMobile,
            contributors = contributors2024,
            onClick = onContributorClick,
        )
        Spacer(modifier = if (isMobile) Modifier.height(24.dp) else Modifier.height(55.dp))
        Sponsors(
            isMobile = isMobile,
            title = "후원자",
            sponsors = sponsors2025,
        )
        Spacer(modifier = if (isMobile) Modifier.height(32.dp) else Modifier.height(58.dp))
        HorizontalDivider(color = Color.White, thickness = 1.dp)
        Spacer(modifier = if (isMobile) Modifier.height(40.dp) else Modifier.height(90.dp))
        Contributors(
            title = "2024",
            isMobile = isMobile,
            contributors = contributors2024,
            onClick = onContributorClick,
        )
        Spacer(modifier = if (isMobile) Modifier.height(24.dp) else Modifier.height(40.dp))
        Contributors(
            title = "2023",
            isMobile = isMobile,
            contributors = contributors2024,
            onClick = onContributorClick,
        )
    }
}
