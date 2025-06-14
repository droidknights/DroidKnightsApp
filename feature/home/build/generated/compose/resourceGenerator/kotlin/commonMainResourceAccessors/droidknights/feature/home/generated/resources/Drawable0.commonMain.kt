@file:OptIn(InternalResourceApi::class)

package droidknights.feature.home.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/droidknights.feature.home.generated.resources/"

internal val Res.drawable.ic_location: DrawableResource by lazy {
      DrawableResource("drawable:ic_location", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_location.xml", -1, -1),
      ))
    }

internal val Res.drawable.sponsor_logo_jetbrains: DrawableResource by lazy {
      DrawableResource("drawable:sponsor_logo_jetbrains", setOf(
        ResourceItem(setOf(), "${MD}drawable/sponsor_logo_jetbrains.xml", -1, -1),
      ))
    }

internal val Res.drawable.sponsor_logo_koin: DrawableResource by lazy {
      DrawableResource("drawable:sponsor_logo_koin", setOf(
        ResourceItem(setOf(), "${MD}drawable/sponsor_logo_koin.png", -1, -1),
      ))
    }

internal val Res.drawable.sponsor_logo_revenue_cat: DrawableResource by lazy {
      DrawableResource("drawable:sponsor_logo_revenue_cat", setOf(
        ResourceItem(setOf(), "${MD}drawable/sponsor_logo_revenue_cat.xml", -1, -1),
      ))
    }

internal val Res.drawable.svg_sponsor_tier_gold: DrawableResource by lazy {
      DrawableResource("drawable:svg_sponsor_tier_gold", setOf(
        ResourceItem(setOf(), "${MD}drawable/svg_sponsor_tier_gold.xml", -1, -1),
      ))
    }

internal val Res.drawable.svg_sponsor_tier_platinum: DrawableResource by lazy {
      DrawableResource("drawable:svg_sponsor_tier_platinum", setOf(
        ResourceItem(setOf(), "${MD}drawable/svg_sponsor_tier_platinum.xml", -1, -1),
      ))
    }

internal val Res.drawable.svg_sponsor_tier_silver: DrawableResource by lazy {
      DrawableResource("drawable:svg_sponsor_tier_silver", setOf(
        ResourceItem(setOf(), "${MD}drawable/svg_sponsor_tier_silver.xml", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("ic_location", Res.drawable.ic_location)
  map.put("sponsor_logo_jetbrains", Res.drawable.sponsor_logo_jetbrains)
  map.put("sponsor_logo_koin", Res.drawable.sponsor_logo_koin)
  map.put("sponsor_logo_revenue_cat", Res.drawable.sponsor_logo_revenue_cat)
  map.put("svg_sponsor_tier_gold", Res.drawable.svg_sponsor_tier_gold)
  map.put("svg_sponsor_tier_platinum", Res.drawable.svg_sponsor_tier_platinum)
  map.put("svg_sponsor_tier_silver", Res.drawable.svg_sponsor_tier_silver)
}
