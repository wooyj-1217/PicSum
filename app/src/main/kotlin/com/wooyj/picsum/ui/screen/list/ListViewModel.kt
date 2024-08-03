package com.wooyj.picsum.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.wooyj.picsum.domain.usecase.PicSumListMediatorUseCase
import com.wooyj.picsum.domain.usecase.ToggleFavoriteUseCase
import com.wooyj.picsum.ui.screen.list.model.ListTypeUI
import com.wooyj.picsum.ui.screen.list.model.toListTypeUI
import com.wooyj.picsum.ui.screen.list.model.toPicSumEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel
    @Inject
    constructor(
        private val picSumListMediatorUseCase: PicSumListMediatorUseCase,
        private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    ) : ViewModel() {
        // UI State
        private val _uiState = MutableStateFlow<ListUIState>(ListUIState.None)
        val uiState = _uiState.asStateFlow()

        // Effect
        private val _effect = MutableSharedFlow<ListEffect>()
        val effect = _effect.asSharedFlow()

        init {
            fetchList()
        }

        private fun fetchList() {
            viewModelScope.launch {
                val flow =
                    picSumListMediatorUseCase
                        .invoke(
                            limit = 30,
                            scope = viewModelScope,
                        ).map { pagingData ->
                            pagingData.map { item ->
                                Timber.d("PicSumItem: $item")
                                item.toListTypeUI()
                            }
                        }.catch {
                            Timber.e(it, "Error in fetchList")
                            _uiState.value = ListUIState.Error
                        }

                _uiState.value = ListUIState.Success(flow)
            }
        }

        private fun toggleFavorite(ui: ListTypeUI) {
            viewModelScope.launch {
                val entity = ui.toPicSumEntity()
                toggleFavoriteUseCase.invoke(entity)
            }
        }

        fun onEvent(event: ListEvent) {
            when (event) {
                is ListEvent.OnItemClickEvent -> {
                    viewModelScope.launch {
                        _effect.emit(ListEffect.NavigateToDetail(event.photoId))
                    }
                }

                is ListEvent.OnFavClickEvent -> {
                    toggleFavorite(event.ui)
                }
            }
        }
    }
