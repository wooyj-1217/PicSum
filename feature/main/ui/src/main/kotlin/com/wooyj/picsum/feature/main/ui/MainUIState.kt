package com.wooyj.picsum.feature.main.ui

import com.wooyj.picsum.ui.scheme.BottomNavigationScheme

sealed class MainUIState {
    data object None : MainUIState()

    data object Loading : MainUIState()

    data class Success(
        val bottomNavigationSchemes: List<BottomNavigationScheme>,
    ) : MainUIState()

    data object Error : MainUIState()
}
