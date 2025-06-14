package com.droidknights.app.feature.bookmark.di

import com.droidknights.app.feature.bookmark.BookmarkViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureBookmarkModule = module {
    viewModelOf(::BookmarkViewModel)
}
