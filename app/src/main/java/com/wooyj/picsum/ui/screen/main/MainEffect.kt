package com.wooyj.picsum.ui.screen.main

interface MainEffect {
    data object NavigateToList : MainEffect

    data object NavigateToFavorite : MainEffect

    data object NavigateToSetting : MainEffect
}
