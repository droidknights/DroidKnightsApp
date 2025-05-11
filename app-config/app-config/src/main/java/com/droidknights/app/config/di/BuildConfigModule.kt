package com.droidknights.app.config.di

import com.droidknights.app.config.DroidknightsBuildConfigImpl
import com.droidknights.app.config.api.DroidknightsBuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object BuildConfigModule {

    @Provides
    @Singleton
    fun provideDroidknightsBuildConfig(): DroidknightsBuildConfig =
        DroidknightsBuildConfigImpl()
}
