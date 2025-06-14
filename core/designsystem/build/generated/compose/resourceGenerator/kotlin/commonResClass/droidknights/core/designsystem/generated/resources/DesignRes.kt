@file:OptIn(InternalResourceApi::class)
@file:Suppress(
  "RedundantVisibilityModifier",
  "REDUNDANT_VISIBILITY_MODIFIER",
)

package droidknights.core.designsystem.generated.resources

import kotlin.ByteArray
import kotlin.OptIn
import kotlin.String
import kotlin.Suppress
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.getResourceUri
import org.jetbrains.compose.resources.readResourceBytes

public object DesignRes {
  /**
   * Reads the content of the resource file at the specified path and returns it as a byte array.
   *
   * Example: `val bytes = DesignRes.readBytes("files/key.bin")`
   *
   * @param path The path of the file to read in the compose resource's directory.
   * @return The content of the file as a byte array.
   */
  public suspend fun readBytes(path: String): ByteArray = readResourceBytes("composeResources/droidknights.core.designsystem.generated.resources/" + path)

  /**
   * Returns the URI string of the resource file at the specified path.
   *
   * Example: `val uri = DesignRes.getUri("files/key.bin")`
   *
   * @param path The path of the file in the compose resource's directory.
   * @return The URI string of the file.
   */
  public fun getUri(path: String): String = getResourceUri("composeResources/droidknights.core.designsystem.generated.resources/" + path)

  public object drawable

  public object string

  public object array

  public object plurals

  public object font
}
