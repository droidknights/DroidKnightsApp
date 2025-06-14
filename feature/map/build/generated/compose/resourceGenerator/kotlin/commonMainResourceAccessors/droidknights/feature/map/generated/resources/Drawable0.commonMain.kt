@file:OptIn(InternalResourceApi::class)

package droidknights.feature.map.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/droidknights.feature.map.generated.resources/"

internal val Res.drawable.svg_map_dark_mode: DrawableResource by lazy {
      DrawableResource("drawable:svg_map_dark_mode", setOf(
        ResourceItem(setOf(), "${MD}drawable/svg_map_dark_mode.xml", -1, -1),
      ))
    }

internal val Res.drawable.svg_map_light_mode: DrawableResource by lazy {
      DrawableResource("drawable:svg_map_light_mode", setOf(
        ResourceItem(setOf(), "${MD}drawable/svg_map_light_mode.xml", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("svg_map_dark_mode", Res.drawable.svg_map_dark_mode)
  map.put("svg_map_light_mode", Res.drawable.svg_map_light_mode)
}
