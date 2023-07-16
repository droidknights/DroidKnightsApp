package com.droidknights.app2023.core.data.di

import android.content.Context
import com.droidknights.app2023.core.data.api.fake.AssetsGithubRawApi
import com.droidknights.app2023.core.data.repository.ContributorRepository
import com.droidknights.app2023.core.data.repository.DefaultContributorRepository
import com.droidknights.app2023.core.data.repository.DefaultSponsorRepository
import com.droidknights.app2023.core.data.repository.SponsorRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {
    
    @Binds
    abstract fun bindsContributorRepository(
        repository: DefaultContributorRepository,
    ): ContributorRepository
    
    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {
        
        @Provides
        @Singleton
        fun provideSponsorRepository(
            @ApplicationContext context: Context,
        ): SponsorRepository {
            return DefaultSponsorRepository(AssetsGithubRawApi(context))
        }
    }
}
