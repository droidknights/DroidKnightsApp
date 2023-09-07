package com.droidknights.app2023.feature.bookmark.api

import androidx.navigation.NavOptions
import com.droidknights.app2023.feature.nav.DroidKnightsNavController

interface BookmarkNavController : DroidKnightsNavController<BookmarkNavControllerInfo>

data class BookmarkNavControllerInfo(
    val navOptions: NavOptions,
)
