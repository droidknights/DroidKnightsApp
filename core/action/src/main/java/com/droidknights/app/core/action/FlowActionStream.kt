package com.droidknights.app.core.action

import kotlinx.coroutines.flow.Flow

/**
 * Action 이벤트 처리를 공통화 하기 위함
 */
interface FlowActionStream {

    fun flowAction(): Flow<Action>

    fun nextAction(action: Action)
}
