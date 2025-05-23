package com.droidknights.app.feature.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Icon
import droidknights.feature.main.generated.resources.Res
import droidknights.feature.main.generated.resources.ic_github
import droidknights.feature.main.generated.resources.web_logo
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun AppBar(
    modifier: Modifier = Modifier,
    onGithubClick: () -> Unit,
) {
    Box(modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .height(40.dp)
                .align(Alignment.CenterStart),
            painter = painterResource(Res.drawable.web_logo),
            contentDescription = null,
        )

        Icon(
            painter = painterResource(Res.drawable.ic_github),
            contentDescription = null,
            modifier = Modifier
                .padding(40.dp, 20.dp)
                .clip(CircleShape)
                .size(40.dp)
                .clickable(onClick = onGithubClick)
                .align(Alignment.CenterEnd),
        )
    }
}
