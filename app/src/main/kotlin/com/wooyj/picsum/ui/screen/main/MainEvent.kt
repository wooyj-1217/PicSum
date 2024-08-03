package com.wooyj.picsum.ui.screen.main

sealed class MainEvent {
    data object OnListClickEvent : MainEvent()

    data object OnFavClickEvent : MainEvent()

    data object OnSettingClickEvent : MainEvent()
}
