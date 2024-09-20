package com.wooyj.picsum.feature.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.wooyj.picsum.feature.detail.domain.DetailUseCase
import com.wooyj.picsum.domain.usecase.local.favorite.RemoveFavoriteNotVisibleUseCase
import com.wooyj.picsum.domain.usecase.local.favorite.UpdateVisibleStateUseCase
import com.wooyj.picsum.feature.detail.ui.model.toDetailTypeUI
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

    // Init Data Load
    init {
        _uiState.value = DetailUIState.Loading

        val currentId = savedStateHandle.get<String>("photoId") ?: ""

        load(currentId)
    }

    private fun load(currentId: String) {
        viewModelScope.launch {
            detailUseCase(currentId = currentId)
                .collectLatest { item ->
                    if (_uiState.value !is DetailUIState.Success) {
                        _uiState.value = DetailUIState.Success(item.toDetailTypeUI())
                        Timber.d("insert: ${item.toDetailTypeUI()}")
                    } else {
                        _uiState.update {
                            (it as DetailUIState.Success).copy(ui = item.toDetailTypeUI())
                        }
                        Timber.d("update: ${item.toDetailTypeUI()}")
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
        // 화면 이동 시 visible false 데이터 삭제
        viewModelScope.launch {
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