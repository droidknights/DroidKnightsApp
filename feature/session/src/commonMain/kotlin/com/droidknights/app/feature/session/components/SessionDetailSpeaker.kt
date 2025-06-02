package com.droidknights.app.feature.session.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.NetworkImage
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.core.model.session.Speaker
import droidknights.feature.session.generated.resources.Res
import droidknights.feature.session.generated.resources.session_detail_speaker
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SessionDetailSpeaker(speaker: Speaker, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        NetworkImage(
            imageUrl = speaker.imageUrl,
            modifier = Modifier
                .size(108.dp)
                .clip(CircleShape),
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(resource = Res.string.session_detail_speaker),
            style = KnightsTheme.typography.labelSmallM,
            color = KnightsTheme.colorScheme.onSurface,
        )
        Text(
            text = speaker.name,
            style = KnightsTheme.typography.titleMediumB,
            color = KnightsTheme.colorScheme.onSurface,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = speaker.introduction,
            style = KnightsTheme.typography.titleSmallR140,
            color = KnightsTheme.colorScheme.onSurface,
        )
    }
}
