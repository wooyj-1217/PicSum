package com.wooyj.picsum.feature.favdetail.ui

import com.wooyj.picsum.feature.favdetail.ui.model.DetailTypeUI

sealed class DetailUIState {
    data object None : DetailUIState()

    data object Loading : DetailUIState()

    data class Success(
        val ui: DetailTypeUI,
    ) : DetailUIState()

    data object Error : DetailUIState()
}
