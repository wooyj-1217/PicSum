package com.wooyj.picsum.ui.screen.main

import com.wooyj.picsum.ui.screen.main.model.BottomNavigationItem

sealed class MainUIState {
    data object None : MainUIState()

    data object Loading : MainUIState()

    data class Success(
        val bottomNavigationItems: List<BottomNavigationItem>,
    ) : MainUIState()

    data object Error : MainUIState()
}
