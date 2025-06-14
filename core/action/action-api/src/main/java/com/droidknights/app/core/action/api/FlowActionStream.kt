package com.droidknights.app.core.action.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance

/**
 * Action 이벤트 처리를 공통화 하기 위함
 */
interface FlowActionStream {

    fun flowAction(): Flow<Action>

    fun nextAction(action: Action)
}

inline fun <reified T : Action> FlowActionStream.onAction(): Flow<T> =
    flowAction()
        .filterIsInstance<T>()
