@file:OptIn(InternalResourceApi::class)

package droidknights.feature.bookmark.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.StringResource

private const val MD: String = "composeResources/droidknights.feature.bookmark.generated.resources/"

internal val Res.string.book_mark_top_bar_title: StringResource by lazy {
      StringResource("string:book_mark_top_bar_title", "book_mark_top_bar_title", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 10, 55),
      ))
    }

internal val Res.string.drag_and_drop: StringResource by lazy {
      StringResource("string:drag_and_drop", "drag_and_drop", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 66, 49),
      ))
    }

internal val Res.string.edit_button_confirm_label: StringResource by lazy {
      StringResource("string:edit_button_confirm_label", "edit_button_confirm_label", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 116, 41),
      ))
    }

internal val Res.string.edit_button_edit_label: StringResource by lazy {
      StringResource("string:edit_button_edit_label", "edit_button_edit_label", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 158, 38),
      ))
    }

internal val Res.string.empty_bookmark_item_description: StringResource by lazy {
      StringResource("string:empty_bookmark_item_description", "empty_bookmark_item_description", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 197, 91),
      ))
    }

internal val Res.string.remove_from_bookmark: StringResource by lazy {
      StringResource("string:remove_from_bookmark", "remove_from_bookmark", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 289, 68),
      ))
    }

internal val Res.string.session_room_keynote: StringResource by lazy {
      StringResource("string:session_room_keynote", "session_room_keynote", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 358, 40),
      ))
    }

internal val Res.string.session_room_track_01: StringResource by lazy {
      StringResource("string:session_room_track_01", "session_room_track_01", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 399, 41),
      ))
    }

internal val Res.string.session_room_track_02: StringResource by lazy {
      StringResource("string:session_room_track_02", "session_room_track_02", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 441, 41),
      ))
    }

internal val Res.string.session_time_format: StringResource by lazy {
      StringResource("string:session_time_format", "session_time_format", setOf(
        ResourceItem(setOf(), "${MD}values/strings.commonMain.cvr", 483, 35),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainString0Resources(map: MutableMap<String, StringResource>) {
  map.put("book_mark_top_bar_title", Res.string.book_mark_top_bar_title)
  map.put("drag_and_drop", Res.string.drag_and_drop)
  map.put("edit_button_confirm_label", Res.string.edit_button_confirm_label)
  map.put("edit_button_edit_label", Res.string.edit_button_edit_label)
  map.put("empty_bookmark_item_description", Res.string.empty_bookmark_item_description)
  map.put("remove_from_bookmark", Res.string.remove_from_bookmark)
  map.put("session_room_keynote", Res.string.session_room_keynote)
  map.put("session_room_track_01", Res.string.session_room_track_01)
  map.put("session_room_track_02", Res.string.session_room_track_02)
  map.put("session_time_format", Res.string.session_time_format)
}
