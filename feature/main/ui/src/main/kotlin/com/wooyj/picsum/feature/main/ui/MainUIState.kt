package com.wooyj.picsum.feature.main.ui

import com.wooyj.picsum.feature.main.ui.model.BottomNavigationItem

sealed class MainUIState {
    data object None : MainUIState()

    data object Loading : MainUIState()

    data class Success(
        val bottomNavigationItems: List<BottomNavigationItem>,
    ) : MainUIState()

    data object Error : MainUIState()
}
