@file:OptIn(InternalResourceApi::class)

package droidknights.feature.contributor.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String =
    "composeResources/droidknights.feature.contributor.generated.resources/"

internal val Res.drawable.ic_contributors: DrawableResource by lazy {
      DrawableResource("drawable:ic_contributors", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_contributors.xml", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("ic_contributors", Res.drawable.ic_contributors)
}
