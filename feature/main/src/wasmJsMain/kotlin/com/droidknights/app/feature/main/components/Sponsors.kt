import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidknights.app.core.designsystem.components.Text
import com.droidknights.app.core.designsystem.theme.KnightsTheme

@Composable
internal fun Sponsors(
    title: String,
    sponsors: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        Text(
            text = title,
            style = KnightsTheme.typography.headlineSmallBL,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            sponsors.forEach { sponsor ->
                Text(
                    text = sponsor,
                    style = KnightsTheme.typography.headlineSmallBL,
                )
            }
            Text(
                text = "후원 감사합니다",
                style = KnightsTheme.typography.headlineSmallBL,
                color = KnightsTheme.colorScheme.borderColor,
            )
        }
    }
}
