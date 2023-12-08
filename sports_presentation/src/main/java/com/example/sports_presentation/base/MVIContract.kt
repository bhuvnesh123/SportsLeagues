package com.example.sports_presentation.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVIContract<VS, VI, SI> {

    fun sendIntent(vi: VI)

    fun createInitialState(): VS

    val viewState: StateFlow<VS>

    val sideEffect: Flow<SI>

}