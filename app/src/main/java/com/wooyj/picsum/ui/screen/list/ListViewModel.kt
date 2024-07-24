package com.wooyj.picsum.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.wooyj.picsum.domain.repository.FavoriteRepository
import com.wooyj.picsum.domain.usecase.GetPicSumListUseCase
import com.wooyj.picsum.ui.screen.list.model.ListTypeUI
import com.wooyj.picsum.ui.screen.list.model.toPicSumEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel
    @Inject
    constructor(
        private val useCase: GetPicSumListUseCase,
        private val repository: FavoriteRepository,
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
                val favoriteFlow = repository.getFavoriteList() // DB (Room)  Flow<List<PicSumEntity>>
                val pagingFlow = useCase(limit = 30).cachedIn(viewModelScope) // API Flow<PagingData<PicSumItemDTO>>
                // TODO("이렇게 하면 당연하게도 전체 다시 그림")
//                combine(favoriteFlow, pagingFlow) { favoriteList, pagingData ->
//                    pagingData.map { picSumItem ->
//                        val isFavorite = favoriteList.any { it.id == picSumItem.id }
//                        picSumItem.toListTypeUI(isFavorite)
//                    }
//                }.collectLatest {
//                    if (uiState.value is ListUIState.None) {
//                        _uiState.value = ListUIState.Success(flowOf(latest))
//                    } else {
//                        _uiState.update {
//                            (_uiState.value as ListUIState.Success).copy(favoriteList = )
//                        }
//                    }
//                }
            }
        }

        private fun toggleLike(ui: ListTypeUI) {
            viewModelScope.launch {
                Timber.d("toggleLike: ViewModelScope")
                val entity = ui.toPicSumEntity()
                if (!repository.added(entity)) {
                    repository.addFavorite(entity)
                } else {
                    repository.removeFavorite(entity)
                }
            }
        }

        fun onEvent(event: ListEvent) {
            when (event) {
                is ListEvent.OnItemClickEvent -> {
                    viewModelScope.launch {
                        _effect.emit(ListEffect.NavigateToDetail(event.photoId))
                    }
                }

                is ListEvent.OnLikeClickEvent -> {
                    toggleLike(event.ui)
                }
            }
        }
    }
