package com.wooyj.picsum.ui

import com.wooyj.picsum.ui.base.BaseEvent

sealed class MainEvent : BaseEvent {
    data object OnListClickEvent : MainEvent()

    data object OnFavClickEvent : MainEvent()

    data object OnSettingClickEvent : MainEvent()

    data class OnReceiveErrorEvent(
        val error: String,
    ) : MainEvent()
}
