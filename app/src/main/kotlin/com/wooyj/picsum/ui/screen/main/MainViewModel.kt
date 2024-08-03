package com.wooyj.picsum.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wooyj.picsum.ui.navigation.Screen
import com.wooyj.picsum.ui.screen.main.model.BottomNavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor() : ViewModel() {
        // UI State
        private val _uiState = MutableStateFlow<MainUIState>(MainUIState.None)
        val uiState: StateFlow<MainUIState> = _uiState.asStateFlow()

        // Effect
        private val _effect: MutableSharedFlow<MainEffect> = MutableSharedFlow()
        val effect = _effect.asSharedFlow()

        init {
            _uiState.value =
                MainUIState.Success(
                    bottomNavigationItems = BottomNavigationItem.entries,
                )
        }

        private fun selectBottomNavigationItem(route: String) {
            if (uiState.value is MainUIState.Success) {
                val currentState = uiState.value as MainUIState.Success
                val newItems =
                    currentState.bottomNavigationItems.map {
                        it.copy(selected = it.route == route)
                    }

                _uiState.update {
                    currentState.copy(bottomNavigationItems = newItems)
                }
            }
        }

        fun onEvent(event: MainEvent) {
            when (event) {
                is MainEvent.OnListClickEvent -> {
                    selectBottomNavigationItem(Screen.List.route)
                    viewModelScope.launch {
                        _effect.emit(MainEffect.NavigateToList)
                    }
                }

                is MainEvent.OnFavClickEvent -> {
                    selectBottomNavigationItem(Screen.Favorite.route)
                    viewModelScope.launch {
                        _effect.emit(MainEffect.NavigateToFavorite)
                    }
                }

                is MainEvent.OnSettingClickEvent -> {
                    selectBottomNavigationItem(Screen.Setting.route)
                    viewModelScope.launch {
                        _effect.emit(MainEffect.NavigateToSetting)
                    }
                }
            }
        }
    }
