package com.example.sports_presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<VS : ViewState, VI : ViewIntent, SI : SideEffect> : ViewModel() {


    private val initialState: VS by lazy { createInitialState() }
    abstract fun createInitialState(): VS

    protected val _state = MutableStateFlow(initialState)
    val viewState: StateFlow<VS> get() = _state      // StateFlow always have a state.We always want to receive last view state when UI become visible.

    protected val _sideEffect = Channel<SI>()
    val sideEffect: Channel<SI> get() = _sideEffect    // Because Channel are consumed exactly once by a single subscriber and we do not need to show side effect again when orientation changed or UI become visible again. Simply we want to replicate SingleLiveEvent behavior


    abstract fun sendIntent(vi: VI)
}