@file:OptIn(InternalResourceApi::class)

package droidknights.feature.map.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String = "composeResources/droidknights.feature.map.generated.resources/"

internal val Res.string.map_title: StringResource by lazy {
      StringResource("string:map_title", "map_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 29),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("map_title", Res.string.map_title)
}
