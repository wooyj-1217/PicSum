package com.wooyj.picsum.ui.screen.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wooyj.picsum.ui.screen.detail.DetailEffect
import com.wooyj.picsum.ui.screen.detail.DetailEvent
import com.wooyj.picsum.ui.screen.detail.DetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
    @Inject
    constructor() :
    ViewModel(),
        DetailViewModel {
        private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.None)
        override val uiState: StateFlow<DetailUIState> = _uiState.asStateFlow()

        private val _effect = MutableSharedFlow<DetailEffect>()
        override val effect: SharedFlow<DetailEffect> = _effect.asSharedFlow()

        private fun toggleFavorite() {
            if (uiState.value is DetailUIState.Success) {
                val currentState = uiState.value as DetailUIState.Success
                val newItem =
                    currentState.imageWithFavoriteUIState.copy(
                        favorite = !currentState.imageWithFavoriteUIState.favorite,
                    )
                _uiState.update {
                    currentState.copy(imageWithFavoriteUIState = newItem)
                }
            }
        }

        override fun onEvent(event: DetailEvent) {
            when (event) {
                is DetailEvent.OnNextClick -> {
                    viewModelScope.launch {
                        _effect.emit(DetailEffect.NavigateToNext)
                    }
                }

                is DetailEvent.OnBeforeClick -> {
                    viewModelScope.launch {
                        _effect.emit(DetailEffect.NavigateToBefore)
                    }
                }

                is DetailEvent.OnFavoriteClick -> {
                    viewModelScope.launch {
                        toggleFavorite()
                    }
                }
            }
        }
    }
