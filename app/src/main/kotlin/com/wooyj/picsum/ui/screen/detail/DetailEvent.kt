package com.wooyj.picsum.ui.screen.detail

import com.wooyj.picsum.ui.base.BaseEvent

sealed class DetailEvent : BaseEvent {
    data class OnNextClick(
        val photoId: String,
    ) : DetailEvent()

    data class OnBeforeClick(
        val photoId: String,
    ) : DetailEvent()

    data class OnFavoriteClick(
        val photoId: String,
    ) : DetailEvent()
}
