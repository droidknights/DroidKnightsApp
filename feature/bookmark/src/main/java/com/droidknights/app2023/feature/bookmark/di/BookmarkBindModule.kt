package com.droidknights.app2023.feature.bookmark.di

import com.droidknights.app2023.feature.bookmark.api.BookmarkNavController
import com.droidknights.app2023.feature.bookmark.api.BookmarkNavGraph
import com.droidknights.app2023.feature.bookmark.navigation.BookmarkNavControllerImpl
import com.droidknights.app2023.feature.bookmark.navigation.BookmarkNavGraphImpl
import com.droidknights.app2023.feature.bookmark.navigation.BookmarkTab
import com.droidknights.app2023.feature.nav.DroidKnightsTab
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
internal abstract class BookmarkBindModule {
    @Binds
    abstract fun bookmarkNavControllerImpl(
        dataSource: BookmarkNavControllerImpl,
    ): BookmarkNavController

    @Binds
    abstract fun bookmarkNavGraphImpl(
        dataSource: BookmarkNavGraphImpl,
    ): BookmarkNavGraph

    @Binds
    @IntoSet
    abstract fun bookmarkTab(
        dataSource: BookmarkTab,
    ): DroidKnightsTab
}
