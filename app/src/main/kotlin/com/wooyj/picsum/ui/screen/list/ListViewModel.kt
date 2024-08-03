package com.wooyj.picsum.ui.screen.list

import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.wooyj.picsum.domain.usecase.GetPicSumListMediatorUseCase
import com.wooyj.picsum.domain.usecase.ToggleFavoriteUseCase
import com.wooyj.picsum.ui.base.BaseViewModel
import com.wooyj.picsum.ui.screen.list.model.ListTypeUI
import com.wooyj.picsum.ui.screen.list.model.toListTypeUI
import com.wooyj.picsum.ui.screen.list.model.toPicSumEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel
    @Inject
    constructor(
        private val getPicSumListMediatorUseCase: GetPicSumListMediatorUseCase,
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
                    getPicSumListMediatorUseCase(
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

        // Flow 활용
        private fun toggleFavorite(ui: ListTypeUI) {
            val entity = ui.toPicSumEntity()
            toggleFavoriteUseCase(entity)
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
                    toggleFavorite(event.ui)
                }
            }
        }
    }

// Flow

// 1. 좋아요 기능 토글
// param : Item
// 1 - 1 : 좋아요 == true
// Return : 좋아요 = false
// UseCase -> Item.favorite -> removeFavorite(item / item.id)
// 1 - 2 : 좋아요 == false
// return : 좋아요 = true
// UseCase -> Item.favorite -> addFavorite(item / item.id)

// param : Item.id
// 1 - 1 : 좋아요 == true
// Return : 좋아요 = false
// UseCase -> getItem(id) -> Item.favorite -> removeFavorite(item.id)
// 1 - 2 : 좋아요 == false
// return : 좋아요 = true
// UseCase -> getItem(id) -> Item.favorite -> addFavorite(item)
