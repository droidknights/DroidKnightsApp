@file:OptIn(InternalResourceApi::class)

package droidknights.feature.main.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/droidknights.feature.main.generated.resources/"

internal val Res.drawable.ic_battery: DrawableResource by lazy {
      DrawableResource("drawable:ic_battery", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_battery.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_bookmark: DrawableResource by lazy {
      DrawableResource("drawable:ic_bookmark", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_bookmark.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_branch: DrawableResource by lazy {
      DrawableResource("drawable:ic_branch", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_branch.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_github: DrawableResource by lazy {
      DrawableResource("drawable:ic_github", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_github.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_home: DrawableResource by lazy {
      DrawableResource("drawable:ic_home", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_home.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_setting: DrawableResource by lazy {
      DrawableResource("drawable:ic_setting", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_setting.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_signal: DrawableResource by lazy {
      DrawableResource("drawable:ic_signal", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_signal.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_wifi: DrawableResource by lazy {
      DrawableResource("drawable:ic_wifi", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_wifi.xml", -1, -1),
      ))
    }

internal val Res.drawable.img_front_camera: DrawableResource by lazy {
      DrawableResource("drawable:img_front_camera", setOf(
        ResourceItem(setOf(), "${MD}drawable/img_front_camera.png", -1, -1),
      ))
    }

internal val Res.drawable.web_logo: DrawableResource by lazy {
      DrawableResource("drawable:web_logo", setOf(
        ResourceItem(setOf(), "${MD}drawable/web_logo.xml", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("ic_battery", Res.drawable.ic_battery)
  map.put("ic_bookmark", Res.drawable.ic_bookmark)
  map.put("ic_branch", Res.drawable.ic_branch)
  map.put("ic_github", Res.drawable.ic_github)
  map.put("ic_home", Res.drawable.ic_home)
  map.put("ic_setting", Res.drawable.ic_setting)
  map.put("ic_signal", Res.drawable.ic_signal)
  map.put("ic_wifi", Res.drawable.ic_wifi)
  map.put("img_front_camera", Res.drawable.img_front_camera)
  map.put("web_logo", Res.drawable.web_logo)
}
