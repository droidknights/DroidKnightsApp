package com.droidknights.app.core.domain.contributor.di

import com.droidknights.app.core.domain.contributor.api.GetContributorsUseCase
import com.droidknights.app.core.domain.contributor.usecase.GetContributorsUseCaseImpl
import org.koin.dsl.module

val coreDomainContributorModule = module {
    factory<GetContributorsUseCase> { GetContributorsUseCaseImpl(get()) }
}
