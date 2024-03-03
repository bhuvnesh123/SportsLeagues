package com.example.sports_presentation.base

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
/**
 * Defines the contract for a Model-View-Intent (MVI) architecture component.
 *
 * @param VS The type of the view state.
 * @param VI The type of the view intent.
 * @param SI The type of the side effect.
 */
interface MVIContract<VS, VI, SI> {

    fun sendIntent(viewIntent: VI)

    val viewState: StateFlow<VS>

    val sideEffect: SharedFlow<SI>

    fun updateViewState(viewState: VS)

    suspend fun emitSideEffect(sideEffect: SI)
}
