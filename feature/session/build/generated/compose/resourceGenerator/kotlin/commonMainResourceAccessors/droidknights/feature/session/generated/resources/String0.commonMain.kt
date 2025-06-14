@file:OptIn(InternalResourceApi::class)

package droidknights.feature.session.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String = "composeResources/droidknights.feature.session.generated.resources/"

internal val Res.string.bookmark: StringResource by lazy {
      StringResource("string:bookmark", "bookmark", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 28),
      ))
    }

internal val Res.string.footer_text: StringResource by lazy {
      StringResource("string:footer_text", "footer_text", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 39, 43),
      ))
    }

internal val Res.string.session_category: StringResource by lazy {
      StringResource("string:session_category", "session_category", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 83, 40),
      ))
    }

internal val Res.string.session_detail_bookmark_popup_message: StringResource by lazy {
      StringResource("string:session_detail_bookmark_popup_message", "session_detail_bookmark_popup_message", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 124, 133),
      ))
    }

internal val Res.string.session_detail_speaker: StringResource by lazy {
      StringResource("string:session_detail_speaker", "session_detail_speaker", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 258, 42),
      ))
    }

internal val Res.string.session_detail_title: StringResource by lazy {
      StringResource("string:session_detail_title", "session_detail_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 301, 56),
      ))
    }

internal val Res.string.session_detail_unbookmark_popup_message: StringResource by lazy {
      StringResource("string:session_detail_unbookmark_popup_message", "session_detail_unbookmark_popup_message", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 358, 95),
      ))
    }

internal val Res.string.session_overview_title: StringResource by lazy {
      StringResource("string:session_overview_title", "session_overview_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 454, 50),
      ))
    }

internal val Res.string.session_room_etc: StringResource by lazy {
      StringResource("string:session_room_etc", "session_room_etc", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 505, 40),
      ))
    }

internal val Res.string.session_room_track_01: StringResource by lazy {
      StringResource("string:session_room_track_01", "session_room_track_01", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 546, 41),
      ))
    }

internal val Res.string.session_room_track_02: StringResource by lazy {
      StringResource("string:session_room_track_02", "session_room_track_02", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 588, 41),
      ))
    }

internal val Res.string.session_time_fmt: StringResource by lazy {
      StringResource("string:session_time_fmt", "session_time_fmt", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 630, 40),
      ))
    }

internal val Res.string.session_title: StringResource by lazy {
      StringResource("string:session_title", "session_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 671, 41),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("bookmark", Res.string.bookmark)
  map.put("footer_text", Res.string.footer_text)
  map.put("session_category", Res.string.session_category)
  map.put("session_detail_bookmark_popup_message", Res.string.session_detail_bookmark_popup_message)
  map.put("session_detail_speaker", Res.string.session_detail_speaker)
  map.put("session_detail_title", Res.string.session_detail_title)
  map.put("session_detail_unbookmark_popup_message", Res.string.session_detail_unbookmark_popup_message)
  map.put("session_overview_title", Res.string.session_overview_title)
  map.put("session_room_etc", Res.string.session_room_etc)
  map.put("session_room_track_01", Res.string.session_room_track_01)
  map.put("session_room_track_02", Res.string.session_room_track_02)
  map.put("session_time_fmt", Res.string.session_time_fmt)
  map.put("session_title", Res.string.session_title)
}
