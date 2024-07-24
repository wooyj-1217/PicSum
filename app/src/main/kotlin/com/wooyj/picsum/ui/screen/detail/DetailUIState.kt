package com.wooyj.picsum.ui.screen.detail

import com.wooyj.picsum.ui.common.ImageWithFavoriteUIState

sealed class DetailUIState {
    data object None : DetailUIState()

    data object Loading : DetailUIState()

    data class Success(
        val imageWithFavoriteUIState: ImageWithFavoriteUIState,
    ) : DetailUIState()

    data object Error : DetailUIState()
}
