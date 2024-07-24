package com.wooyj.picsum.ui.screen.list

import androidx.paging.PagingData
import com.wooyj.picsum.ui.screen.list.model.ListTypeUI
import kotlinx.coroutines.flow.Flow

sealed class ListUIState {
    data object None : ListUIState()

    data object Loading : ListUIState()

    data class Success(
        val list: Flow<PagingData<ListTypeUI>>,
//        val list: Flow<PagingData<PicSumItemDTO>>,
//        val favoriteList: Flow<PicSumEntity>,
    ) : ListUIState()

    data object Error : ListUIState()
}
