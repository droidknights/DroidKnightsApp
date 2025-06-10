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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Row(
        modifier = modifier,
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
        shape = RoundedCornerShape(8.dp),
        color = KnightsTheme.colorScheme.primarySurface,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp),
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(Res.drawable.ic_branch),
                contentDescription = null,
            )
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = branch.name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                )
                Text(
                    text = "#${branch.tag}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = LocalContentColor.current.copy(alpha = 0.5F),
                    maxLines = 1,
                )
            }
        }
    }
}
