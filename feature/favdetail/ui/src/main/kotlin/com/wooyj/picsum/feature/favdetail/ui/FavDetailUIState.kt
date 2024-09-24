package com.wooyj.picsum.feature.favdetail.ui

import com.wooyj.picsum.feature.favdetail.ui.model.DetailTypeUI

sealed class FavDetailUIState {
    data object None : FavDetailUIState()

    data object Loading : FavDetailUIState()

    data class Success(
        val ui: DetailTypeUI,
    ) : FavDetailUIState()

    data object Error : FavDetailUIState()
}
