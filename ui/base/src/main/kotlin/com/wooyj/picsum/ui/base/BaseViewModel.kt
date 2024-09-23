package com.wooyj.picsum.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<EV : BaseEvent, EF : BaseEffect> : ViewModel() {
    private val _effect = MutableSharedFlow<EF>()
    val effect = _effect.asSharedFlow()

    abstract fun onEvent(event: EV)

    fun sendEffect(effect: EF) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
