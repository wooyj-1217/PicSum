package com.wooyj.picsum.ui

sealed class MainUIState {
    data object None : MainUIState()

    data object Loading : MainUIState()

//    data class Success(
//        val bottomNavigationSchemes: List<BottomNavigationScheme>,
//    ) : MainUIState()
    data object Success : MainUIState()

    data object Error : MainUIState()
}
