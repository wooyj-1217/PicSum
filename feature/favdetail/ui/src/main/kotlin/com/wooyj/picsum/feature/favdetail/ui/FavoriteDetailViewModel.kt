package com.wooyj.picsum.feature.favdetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.wooyj.picsum.feature.favdetail.domain.FavoriteDetailUseCase
import com.wooyj.picsum.feature.favdetail.domain.RemoveFavoriteNotVisibleUseCase
import com.wooyj.picsum.feature.favdetail.domain.UpdateVisibleStateUseCase
import com.wooyj.picsum.feature.favdetail.ui.model.toFavoriteDetailTypeUI
import com.wooyj.picsum.ui.base.BaseViewModel
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
    ) : BaseViewModel<FavDetailEvent, FavDetailEffect>() {
        // UI State
        private val _uiState = MutableStateFlow<FavDetailUIState>(FavDetailUIState.None)
        val uiState: StateFlow<FavDetailUIState> = _uiState.asStateFlow()

        // Init Data Load
        init {
            _uiState.value = FavDetailUIState.Loading

            val currentId = savedStateHandle.get<String>("photoId") ?: ""

            load(currentId)
        }

        private fun load(currentId: String) {
            viewModelScope.launch {
                favoriteDetailUseCase(currentId = currentId)
                    .collectLatest { item ->
                        if (_uiState.value !is FavDetailUIState.Success) {
                            _uiState.value = FavDetailUIState.Success(item.toFavoriteDetailTypeUI())
                        } else {
                            _uiState.update {
                                (it as FavDetailUIState.Success).copy(ui = item.toFavoriteDetailTypeUI())
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

        override fun onEvent(event: FavDetailEvent) {
            when (event) {
                is FavDetailEvent.OnBeforeClick -> {
                    goToId(event.photoId)
                }

                is FavDetailEvent.OnNextClick -> {
                    goToId(event.photoId)
                }

                is FavDetailEvent.OnFavoriteClick -> {
                    toggleFavorite(event.photoId)
                }
            }
        }
    }
