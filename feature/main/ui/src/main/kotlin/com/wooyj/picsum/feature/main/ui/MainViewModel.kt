package com.wooyj.picsum.feature.main.ui

import androidx.lifecycle.viewModelScope
import com.wooyj.picsum.ui.base.BaseViewModel
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor() : BaseViewModel<MainEvent, MainEffect>() {
        // UI State
        private val _uiState = MutableStateFlow<MainUIState>(MainUIState.None)
        val uiState: StateFlow<MainUIState> = _uiState.asStateFlow()

        init {
            _uiState.value =
                MainUIState.Success(
                    bottomNavigationSchemes = BottomNavigationScheme.entries,
                )
        }

        override fun onEvent(event: MainEvent) {
            when (event) {
                is MainEvent.OnListClickEvent -> {
                    viewModelScope.launch {
                        sendEffect(MainEffect.NavigateToList)
                    }
                }

                is MainEvent.OnFavClickEvent -> {
                    viewModelScope.launch {
                        sendEffect(MainEffect.NavigateToFavorite)
                    }
                }

                is MainEvent.OnSettingClickEvent -> {
                    viewModelScope.launch {
                        sendEffect(MainEffect.NavigateToSetting)
                    }
                }

                is MainEvent.OnReviceErrorEvent -> Unit
            }
        }
    }
