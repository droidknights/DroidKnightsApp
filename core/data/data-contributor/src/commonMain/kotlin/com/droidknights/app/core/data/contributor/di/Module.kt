package com.droidknights.app.core.data.contributor.di

import com.droidknights.app.core.data.contributor.ContributorRepositoryImpl
import com.droidknights.app.core.data.contributor.api.DroidknightsContributorsApi
import com.droidknights.app.core.data.contributor.api.GithubContributorsApi
import com.droidkniths.app.core.data.contributor.api.ContributorRepository
import org.koin.dsl.module

val coreDataContributorModule = module {
    single { GithubContributorsApi(get()) }
    single { DroidknightsContributorsApi(get()) }
    single<ContributorRepository> { ContributorRepositoryImpl(get(), get()) }
}
