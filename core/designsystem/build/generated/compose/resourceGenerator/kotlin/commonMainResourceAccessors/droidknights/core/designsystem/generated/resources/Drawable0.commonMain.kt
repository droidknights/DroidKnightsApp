@file:OptIn(InternalResourceApi::class)

package droidknights.core.designsystem.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String =
    "composeResources/droidknights.core.designsystem.generated.resources/"

public val DesignRes.drawable.ic_arrow_back: DrawableResource by lazy {
      DrawableResource("drawable:ic_arrow_back", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_arrow_back.xml", -1, -1),
      ))
    }

public val DesignRes.drawable.ic_close: DrawableResource by lazy {
      DrawableResource("drawable:ic_close", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_close.xml", -1, -1),
      ))
    }

public val DesignRes.drawable.ic_flagbookmark: DrawableResource by lazy {
      DrawableResource("drawable:ic_flagbookmark", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_flagbookmark.xml", -1, -1),
      ))
    }

public val DesignRes.drawable.ic_session_bookmark: DrawableResource by lazy {
      DrawableResource("drawable:ic_session_bookmark", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_session_bookmark.xml", -1, -1),
      ))
    }

public val DesignRes.drawable.ic_session_bookmark_filled: DrawableResource by lazy {
      DrawableResource("drawable:ic_session_bookmark_filled", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_session_bookmark_filled.xml", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("ic_arrow_back", DesignRes.drawable.ic_arrow_back)
  map.put("ic_close", DesignRes.drawable.ic_close)
  map.put("ic_flagbookmark", DesignRes.drawable.ic_flagbookmark)
  map.put("ic_session_bookmark", DesignRes.drawable.ic_session_bookmark)
  map.put("ic_session_bookmark_filled", DesignRes.drawable.ic_session_bookmark_filled)
}
