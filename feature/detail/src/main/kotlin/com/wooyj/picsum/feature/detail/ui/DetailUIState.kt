package com.wooyj.picsum.feature.detail.ui

import com.wooyj.picsum.feature.detail.ui.model.DetailTypeUI

sealed class DetailUIState {
    data object None : DetailUIState()

    data object Loading : DetailUIState()

    data class Success(
        val ui: DetailTypeUI,
    ) : DetailUIState()

    data object Error : DetailUIState()
}
