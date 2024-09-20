package com.wooyj.picsum.feature.favorite.list.ui

import androidx.paging.PagingData
import com.wooyj.picsum.feature.list.ui.model.ListTypeUI
import kotlinx.coroutines.flow.Flow

sealed class ListUIState {
    data object None : ListUIState()

    data object Loading : ListUIState()

    data class Success(
        val list: Flow<PagingData<ListTypeUI>>,
    ) : ListUIState()

    data object Error : ListUIState()
}
