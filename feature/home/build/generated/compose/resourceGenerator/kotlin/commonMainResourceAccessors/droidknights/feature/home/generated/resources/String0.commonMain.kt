@file:OptIn(InternalResourceApi::class)

package droidknights.feature.home.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String = "composeResources/droidknights.feature.home.generated.resources/"

internal val Res.string.home_contributor_card_desc: StringResource by lazy {
      StringResource("string:home_contributor_card_desc", "home_contributor_card_desc", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 74),
      ))
    }

internal val Res.string.home_contributor_card_title: StringResource by lazy {
      StringResource("string:home_contributor_card_title", "home_contributor_card_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 85, 103),
      ))
    }

internal val Res.string.home_map_card_desc: StringResource by lazy {
      StringResource("string:home_map_card_desc", "home_map_card_desc", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 189, 78),
      ))
    }

internal val Res.string.home_map_card_title: StringResource by lazy {
      StringResource("string:home_map_card_title", "home_map_card_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 268, 71),
      ))
    }

internal val Res.string.home_session_card_desc: StringResource by lazy {
      StringResource("string:home_session_card_desc", "home_session_card_desc", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 340, 122),
      ))
    }

internal val Res.string.home_session_card_title: StringResource by lazy {
      StringResource("string:home_session_card_title", "home_session_card_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 463, 79),
      ))
    }

internal val Res.string.home_sponsor_card_desc_template: StringResource by lazy {
      StringResource("string:home_sponsor_card_desc_template", "home_sponsor_card_desc_template", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 543, 127),
      ))
    }

internal val Res.string.home_sponsor_card_title: StringResource by lazy {
      StringResource("string:home_sponsor_card_title", "home_sponsor_card_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 671, 63),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("home_contributor_card_desc", Res.string.home_contributor_card_desc)
  map.put("home_contributor_card_title", Res.string.home_contributor_card_title)
  map.put("home_map_card_desc", Res.string.home_map_card_desc)
  map.put("home_map_card_title", Res.string.home_map_card_title)
  map.put("home_session_card_desc", Res.string.home_session_card_desc)
  map.put("home_session_card_title", Res.string.home_session_card_title)
  map.put("home_sponsor_card_desc_template", Res.string.home_sponsor_card_desc_template)
  map.put("home_sponsor_card_title", Res.string.home_sponsor_card_title)
}
