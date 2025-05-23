package com.droidknights.app.feature.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import com.droidknights.app.core.designsystem.components.Surface
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.designsystem.theme.LocalContentColor
import com.droidknights.app.feature.main.model.ProjectBranch
import droidknights.feature.main.generated.resources.Res
import droidknights.feature.main.generated.resources.ic_branch
import kotlinx.collections.immutable.PersistentList
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ProjectBranchButtons(
    onClick: (ProjectBranch) -> Unit,
    branches: PersistentList<ProjectBranch>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        branches.forEach { branch ->
            ProjectBranchButton(
                onClick = onClick,
                branch = branch,
            )
        }
    }
}

@Composable
private fun ProjectBranchButton(
    onClick: (ProjectBranch) -> Unit,
    branch: ProjectBranch,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = {
            onClick(branch)
        },
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = KnightsTheme.colorScheme.primarySurface,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp),
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(Res.drawable.ic_branch),
                contentDescription = null,
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = branch.name,
                    style = KnightsTheme.typography.titleLargeBL,
                    maxLines = 1,
                )
                Text(
                    text = "#${branch.tag}",
                    style = KnightsTheme.typography.titleMediumR,
                    color = LocalContentColor.current.copy(alpha = 0.5F),
                    maxLines = 1,
                )
            }
        }
    }
}
