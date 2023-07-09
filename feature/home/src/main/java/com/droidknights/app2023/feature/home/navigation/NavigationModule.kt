package com.droidknights.app2023.feature.home.navigation

import com.droidknights.app2023.core.navigation.HomeNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {
    
    @Provides
    fun provideHomeNavigation(): HomeNavigation = HomeNavigationImpl()
}
