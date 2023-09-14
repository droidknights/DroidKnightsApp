package com.droidknights.app2023.feature.setting.opensource

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidknights.app2023.core.designsystem.component.KnightsCard
import com.droidknights.app2023.core.designsystem.theme.Graphite
import com.droidknights.app2023.core.designsystem.theme.KnightsTheme
import com.droidknights.app2023.feature.setting.R
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
internal fun OpenSourceCard(
    modifier: Modifier = Modifier,
    context: Context,
) {
    val titleText = stringResource(id = R.string.oss_license_title)
    val arrowPainter = painterResource(id = R.drawable.icon_arrow_right_yellow01)

    KnightsCard(
        color = Graphite,
        modifier = modifier,
        onClick = {
            context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
    ) {
        Column {
            Text(
                text = titleText,
                style = KnightsTheme.typography.headlineSmallBL,
                modifier = Modifier.padding(top = 24.dp, start = 24.dp),
                color = Color.White
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
