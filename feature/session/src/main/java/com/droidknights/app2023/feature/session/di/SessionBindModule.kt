package com.droidknights.app2023.feature.session.di

import com.droidknights.app2023.feature.session.api.SessionDetailNavController
import com.droidknights.app2023.feature.session.api.SessionNavController
import com.droidknights.app2023.feature.session.api.SessionNavGraph
import com.droidknights.app2023.feature.session.navigation.SessionDetailNavControllerImpl
import com.droidknights.app2023.feature.session.navigation.SessionNavControllerImpl
import com.droidknights.app2023.feature.session.navigation.SessionNavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal abstract class SessionBindModule {
    @Binds
    abstract fun sessionNavControllerImpl(
        dataSource: SessionNavControllerImpl,
    ): SessionNavController

    @Binds
    abstract fun sessionDetailNavControllerImpl(
        dataSource: SessionDetailNavControllerImpl,
    ): SessionDetailNavController

    @Binds
    abstract fun sessionNavGraphImpl(
        dataSource: SessionNavGraphImpl,
    ): SessionNavGraph
}
