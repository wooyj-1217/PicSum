package com.wooyj.picsum.feature.favorite.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.wooyj.picsum.feature.favorite.domain.FavoriteListUseCase
import com.wooyj.picsum.feature.favorite.domain.ToggleFavoriteUseCase
import com.wooyj.picsum.feature.favorite.list.ui.ListEffect
import com.wooyj.picsum.feature.favorite.list.ui.ListEvent
import com.wooyj.picsum.feature.favorite.list.ui.ListUIState
import com.wooyj.picsum.feature.favorite.ui.model.toListTypeUI
import com.wooyj.picsum.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel
    @Inject
    constructor(
        private val favoriteListUseCase: FavoriteListUseCase,
        private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    ) : BaseViewModel<ListEvent, ListEffect>() {
        // UI State
        private val _uiState = MutableStateFlow<ListUIState>(ListUIState.None)
        val uiState = _uiState.asStateFlow()

        init {
            fetchList()
        }

        private fun fetchList() {
            viewModelScope.launch {
                val flow =
                    favoriteListUseCase()
                        .map { pagingData ->
                            pagingData.map {
                                it.toListTypeUI()
                            }
                        }

                _uiState.value = ListUIState.Success(flow)
            }
        }

        // Flow 활용
        private fun toggleFavorite(id: String) {
            toggleFavoriteUseCase(id)
                .onEach {
                    Timber.d("toggleFavorite: $it")
                }.launchIn(viewModelScope)
        }

        // Base Class
        override fun onEvent(event: ListEvent) {
            when (event) {
                is ListEvent.OnItemClickEvent -> {
                    sendEffect(ListEffect.NavigateToDetail(event.photoId))
                }

                is ListEvent.OnFavClickEvent -> {
                    toggleFavorite(event.photoId)
                }
            }
        }
    }
