@file:OptIn(InternalResourceApi::class)

package droidknights.feature.license.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String = "composeResources/droidknights.feature.license.generated.resources/"

internal val Res.string.license_top_app_bar_title: StringResource by lazy {
      StringResource("string:license_top_app_bar_title", "license_top_app_bar_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 69),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("license_top_app_bar_title", Res.string.license_top_app_bar_title)
}
