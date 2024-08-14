package com.wooyj.picsum.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.wooyj.picsum.domain.usecase.favorite.UpdateVisibleStateUseCase
import com.wooyj.picsum.domain.usecase.detail.DetailUseCase
import com.wooyj.picsum.domain.usecase.favorite.RemoveFavoriteNotVisibleUseCase
import com.wooyj.picsum.ui.base.BaseViewModel
import com.wooyj.picsum.ui.screen.detail.model.toDetailTypeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val detailUseCase: DetailUseCase,
        private val toggleFavoriteUseCase: UpdateVisibleStateUseCase,
        private val removeFavoriteNotVisibleUseCase: RemoveFavoriteNotVisibleUseCase,
    ) : BaseViewModel<DetailEvent, DetailEffect>() {
        // UI State
        private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.None)
        val uiState: StateFlow<DetailUIState> = _uiState.asStateFlow()

        // 초기 로드 시 fromDetail을 설정
        private val fromDetail = savedStateHandle.get<String>("photoId") != null

        // currentId
        private var _currentId = MutableStateFlow(savedStateHandle.get<String>("photoId") ?: "")
        val currentId: StateFlow<String> = _currentId.asStateFlow()

        init {
            viewModelScope.launch {
                currentId.collectLatest { id ->
                    detailUseCase(
                        fromDetail = fromDetail, // 처음 한번만 설정
                        currentId = currentId, // 이전, 다음 버튼을 누를 때마다 달라지는 값.
                    ).collectLatest { item ->
                        Timber.d("fetchData: $item")
                        if (item.item != null) {
                            if (_uiState.value !is DetailUIState.Success) {
                                _uiState.value = DetailUIState.Success(item.toDetailTypeUI())
                            } else {
                                _uiState.update {
                                    (it as DetailUIState.Success).copy(ui = item.toDetailTypeUI())
                                }
                            }
                        }
                    }
                }
            }
        }

        private fun toggleFavorite(id: String) {
            toggleFavoriteUseCase(id)
                .onEach {
                    Timber.d("toggleFavorite: $it")
                }.launchIn(viewModelScope)
        }

        private fun goToId(id: String) {
            // 화면 이동 시 visible false 데이터 삭제
            viewModelScope.launch {
                removeFavoriteNotVisibleUseCase()
                _currentId.value = id
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
