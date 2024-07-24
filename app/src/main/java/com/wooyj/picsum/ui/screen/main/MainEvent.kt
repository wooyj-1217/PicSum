package com.wooyj.picsum.ui.screen.main

sealed class MainEvent {
    data object OnListClickEvent : MainEvent()

    data object OnLikeClickEvent : MainEvent()

    data object OnSettingClickEvent : MainEvent()
}
