@file:OptIn(InternalResourceApi::class)

package droidknights.feature.setting.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/droidknights.feature.setting.generated.resources/"

internal val Res.drawable.ic_arrow_right: DrawableResource by lazy {
      DrawableResource("drawable:ic_arrow_right", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_arrow_right.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_disabled: DrawableResource by lazy {
      DrawableResource("drawable:ic_disabled", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_disabled.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_enabled: DrawableResource by lazy {
      DrawableResource("drawable:ic_enabled", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_enabled.xml", -1, -1),
      ))
    }

internal val Res.drawable.img_android: DrawableResource by lazy {
      DrawableResource("drawable:img_android", setOf(
        ResourceItem(setOf(), "${MD}drawable/img_android.png", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("ic_arrow_right", Res.drawable.ic_arrow_right)
  map.put("ic_disabled", Res.drawable.ic_disabled)
  map.put("ic_enabled", Res.drawable.ic_enabled)
  map.put("img_android", Res.drawable.img_android)
}
