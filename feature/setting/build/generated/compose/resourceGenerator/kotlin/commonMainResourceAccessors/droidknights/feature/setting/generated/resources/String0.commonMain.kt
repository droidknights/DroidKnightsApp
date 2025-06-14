@file:OptIn(InternalResourceApi::class)

package droidknights.feature.setting.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String = "composeResources/droidknights.feature.setting.generated.resources/"

internal val Res.string.setting_dark_theme_card_desc_dark_mode: StringResource by lazy {
      StringResource("string:setting_dark_theme_card_desc_dark_mode", "setting_dark_theme_card_desc_dark_mode", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 66),
      ))
    }

internal val Res.string.setting_dark_theme_card_desc_light_mode: StringResource by lazy {
      StringResource("string:setting_dark_theme_card_desc_light_mode", "setting_dark_theme_card_desc_light_mode", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 77, 71),
      ))
    }

internal val Res.string.setting_dark_theme_card_title: StringResource by lazy {
      StringResource("string:setting_dark_theme_card_title", "setting_dark_theme_card_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 149, 45),
      ))
    }

internal val Res.string.setting_open_source_card_title: StringResource by lazy {
      StringResource("string:setting_open_source_card_title", "setting_open_source_card_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 195, 74),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("setting_dark_theme_card_desc_dark_mode", Res.string.setting_dark_theme_card_desc_dark_mode)
  map.put("setting_dark_theme_card_desc_light_mode", Res.string.setting_dark_theme_card_desc_light_mode)
  map.put("setting_dark_theme_card_title", Res.string.setting_dark_theme_card_title)
  map.put("setting_open_source_card_title", Res.string.setting_open_source_card_title)
}
