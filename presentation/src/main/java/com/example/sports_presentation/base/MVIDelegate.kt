package com.example.sports_presentation.base

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
/**
 * A delegate class that implements the MVI contract for a Model-View-Intent (MVI) architecture component.
 *
 * @param VS The type of the view state.
 * @param VI The type of the view intent.
 * @param SI The type of the side effect.
 * @param initialViewState The initial view state of the MVI component.
 */
class MVIDelegate<VS, VI, SI> internal constructor(
    initialViewState: VS,
) : MVIContract<VS, VI, SI> {

    private val _viewState = MutableStateFlow(value = initialViewState)
    override val viewState: StateFlow<VS>
        get() = _viewState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SI>()
    override val sideEffect: SharedFlow<SI>
        get() = _sideEffect.asSharedFlow()

    override suspend fun emitSideEffect(sideEffect: SI) {
        _sideEffect.emit(value = sideEffect)
    }

    override fun updateViewState(viewState: VS) {
        _viewState.update { viewState }
    }

    override fun sendIntent(viewIntent: VI) {
        /*
           This method implementation should be specific to the class delegating its responsibility to the MVI Delegate.
           Hence, the class that delegates its responsibility to the MviDelegate is the one that should implement the
           sendIntent() method to handle the view intent as needed.
        */
    }
}
