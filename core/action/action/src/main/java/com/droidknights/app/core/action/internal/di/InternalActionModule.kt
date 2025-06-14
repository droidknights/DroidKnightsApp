package com.droidknights.app.core.action.internal.di

import com.droidknights.app.core.action.api.FlowActionStream
import com.droidknights.app.core.action.internal.InternalActionImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class InternalActionModule {

    @Binds
    @ViewModelScoped
    abstract fun bindFlowActionStream(
        internalCaAction: InternalActionImpl,
    ): FlowActionStream
}
