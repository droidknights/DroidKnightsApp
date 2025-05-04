import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

private val SansSerifStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
)

// TODO Figma 보고 현행화 해야함
@Immutable
data class KnightsTypography(
    val textStyle1: TextStyle
)

internal val Typography = KnightsTypography(
    textStyle1 = SansSerifStyle
)

val LocalTypography = staticCompositionLocalOf {
    KnightsTypography(
        textStyle1 = SansSerifStyle
    )
}