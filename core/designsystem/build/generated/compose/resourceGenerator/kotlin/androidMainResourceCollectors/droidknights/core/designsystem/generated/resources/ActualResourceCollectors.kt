@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package droidknights.core.designsystem.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.Map
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringArrayResource
import org.jetbrains.compose.resources.StringResource

public actual val DesignRes.allDrawableResources: Map<String, DrawableResource> by lazy {
  val map = mutableMapOf<String, DrawableResource>()
  _collectCommonMainDrawable0Resources(map)
  return@lazy map
}

public actual val DesignRes.allStringResources: Map<String, StringResource> by lazy {
  val map = mutableMapOf<String, StringResource>()
  return@lazy map
}

public actual val DesignRes.allStringArrayResources: Map<String, StringArrayResource> by lazy {
  val map = mutableMapOf<String, StringArrayResource>()
  return@lazy map
}

public actual val DesignRes.allPluralStringResources: Map<String, PluralStringResource> by lazy {
  val map = mutableMapOf<String, PluralStringResource>()
  return@lazy map
}

public actual val DesignRes.allFontResources: Map<String, FontResource> by lazy {
  val map = mutableMapOf<String, FontResource>()
  return@lazy map
}
