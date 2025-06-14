@file:OptIn(InternalResourceApi::class)

package droidknights.feature.contributor.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String =
    "composeResources/droidknights.feature.contributor.generated.resources/"

internal val Res.string.contributor_banner_description: StringResource by lazy {
      StringResource("string:contributor_banner_description", "contributor_banner_description", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 78),
      ))
    }

internal val Res.string.contributor_banner_title: StringResource by lazy {
      StringResource("string:contributor_banner_title", "contributor_banner_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 89, 100),
      ))
    }

internal val Res.string.contributor_chip: StringResource by lazy {
      StringResource("string:contributor_chip", "contributor_chip", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 190, 40),
      ))
    }

internal val Res.string.contributor_title: StringResource by lazy {
      StringResource("string:contributor_title", "contributor_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 231, 45),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("contributor_banner_description", Res.string.contributor_banner_description)
  map.put("contributor_banner_title", Res.string.contributor_banner_title)
  map.put("contributor_chip", Res.string.contributor_chip)
  map.put("contributor_title", Res.string.contributor_title)
}
