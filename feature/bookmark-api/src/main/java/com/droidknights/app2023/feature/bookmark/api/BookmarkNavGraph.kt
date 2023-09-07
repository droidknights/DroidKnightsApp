package com.droidknights.app2023.feature.bookmark.api

import com.droidknights.app2023.feature.nav.DroidKnightsNavGraph

interface BookmarkNavGraph : DroidKnightsNavGraph<BookmarkNavGraphInfo>

data class BookmarkNavGraphInfo(
    val onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
)
