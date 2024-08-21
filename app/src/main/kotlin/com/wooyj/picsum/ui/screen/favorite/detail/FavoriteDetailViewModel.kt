package com.wooyj.picsum.ui.screen.favorite.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.wooyj.picsum.domain.usecase.favorite.FavoriteDetailUseCase
import com.wooyj.picsum.domain.usecase.local.favorite.RemoveFavoriteNotVisibleUseCase
import com.wooyj.picsum.domain.usecase.local.favorite.UpdateVisibleStateUseCase
import com.wooyj.picsum.ui.base.BaseViewModel
import com.wooyj.picsum.ui.screen.detail.DetailEffect
import com.wooyj.picsum.ui.screen.detail.DetailEvent
import com.wooyj.picsum.ui.screen.detail.DetailUIState
import com.wooyj.picsum.ui.screen.detail.model.toFavoriteDetailTypeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val favoriteDetailUseCase: FavoriteDetailUseCase,
        private val toggleFavoriteUseCase: UpdateVisibleStateUseCase,
        private val removeFavoriteNotVisibleUseCase: RemoveFavoriteNotVisibleUseCase,
    ) : BaseViewModel<DetailEvent, DetailEffect>() {
        // UI State
        private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.None)
        val uiState: StateFlow<DetailUIState> = _uiState.asStateFlow()

        // Init Data Load
        init {
            _uiState.value = DetailUIState.Loading

            val currentId = savedStateHandle.get<String>("photoId") ?: ""

            load(currentId)
        }

        private fun load(currentId: String) {
            viewModelScope.launch {
                favoriteDetailUseCase(currentId = currentId)
                    .collectLatest { item ->
                        if (_uiState.value !is DetailUIState.Success) {
                            _uiState.value = DetailUIState.Success(item.toFavoriteDetailTypeUI())
                        } else {
                            _uiState.update {
                                (it as DetailUIState.Success).copy(ui = item.toFavoriteDetailTypeUI())
                            }
                        }
                    }
            }
        }

        private fun toggleFavorite(id: String) {
            viewModelScope.launch {
                toggleFavoriteUseCase(id)
                    .onEach {
                        Timber.d("toggleFavorite: $it")
                    }.collect()
                load(id)
            }
        }

        private fun goToId(id: String) {
            viewModelScope.launch {
                // 화면 이동 시 visible false 데이터 삭제
                removeFavoriteNotVisibleUseCase()
                load(id)
            }
        }

        override fun onEvent(event: DetailEvent) {
            when (event) {
                is DetailEvent.OnBeforeClick -> {
                    goToId(event.photoId)
                }

                is DetailEvent.OnNextClick -> {
                    goToId(event.photoId)
                }

                is DetailEvent.OnFavoriteClick -> {
                    toggleFavorite(event.photoId)
                }
            }
        }
    }
