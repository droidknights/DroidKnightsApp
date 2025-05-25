package com.droidknights.app.feature.setting.component

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.component.KnightsCard
import com.droidknights.app.core.designsystem.theme.KnightsColor
import com.droidknights.app.core.designsystem.theme.KnightsTheme
import com.droidknights.app.feature.setting.R
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
internal fun OpenSourceCard(
    modifier: Modifier = Modifier,
) {
    val titleText = stringResource(id = R.string.oss_license_title)
    val arrowPainter = painterResource(id = R.drawable.icon_arrow_right_yellow01)
    val context = LocalContext.current

    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer) {
        KnightsCard(
            modifier = modifier,
            color = KnightsColor.Graphite,
            onClick = {
                context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
            }
        ) {
            Column {
                Text(
                    text = titleText,
                    style = KnightsTheme.typography.headlineSmallBL,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 24.dp, start = 24.dp)
                )

                Spacer(modifier = Modifier.padding(top = 39.dp))

                Image(
                    painter = arrowPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.End)
                        .padding(end = 24.dp, bottom = 24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOpenSourceCard() {
    OpenSourceCard()
}
