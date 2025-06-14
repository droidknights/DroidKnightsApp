package com.droidknights.app.core.action.internal

import com.droidknights.app.core.action.api.Action
import com.droidknights.app.core.action.api.FlowActionStream
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

/**
 * ViewModelScope 내에서 Action을 하나로 사용하기 위함
 */
@ViewModelScoped
internal class InternalActionImpl @Inject constructor() : FlowActionStream {

    private val flowCaAction = MutableSharedFlow<Action>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override fun flowAction(): Flow<Action> =
        flowCaAction.asSharedFlow()

    override fun nextAction(action: Action) {
        flowCaAction.tryEmit(action)
    }
}
