package com.wooyj.picsum.feature.detail.ui

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
