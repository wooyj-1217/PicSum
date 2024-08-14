package com.wooyj.picsum.ui.screen.detail

import com.wooyj.picsum.ui.screen.detail.model.DetailTypeUI

sealed class DetailUIState {
    data object None : DetailUIState()

    data object Loading : DetailUIState()

    data class Success(
        val ui: DetailTypeUI,
    ) : DetailUIState()

    data object Error : DetailUIState()
}
