package com.droidknights.app.feature.contributor.di

import com.droidknights.app.feature.contributor.ContributorViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureContributorModule =
    module {
        viewModelOf(::ContributorViewModel)
    }
