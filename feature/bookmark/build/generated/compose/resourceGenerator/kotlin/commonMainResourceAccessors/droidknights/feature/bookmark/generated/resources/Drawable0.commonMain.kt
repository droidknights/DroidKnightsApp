@file:OptIn(InternalResourceApi::class)

package droidknights.feature.bookmark.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/droidknights.feature.bookmark.generated.resources/"

internal val Res.drawable.ic_check: DrawableResource by lazy {
      DrawableResource("drawable:ic_check", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_check.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_menu: DrawableResource by lazy {
      DrawableResource("drawable:ic_menu", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_menu.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_trash: DrawableResource by lazy {
      DrawableResource("drawable:ic_trash", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_trash.xml", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("ic_check", Res.drawable.ic_check)
  map.put("ic_menu", Res.drawable.ic_menu)
  map.put("ic_trash", Res.drawable.ic_trash)
}
