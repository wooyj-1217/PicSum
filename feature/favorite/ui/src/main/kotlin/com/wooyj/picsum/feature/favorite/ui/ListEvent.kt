package com.wooyj.picsum.feature.favorite.list.ui

import com.wooyj.picsum.ui.base.BaseEvent

sealed class ListEvent : BaseEvent {
    data class OnItemClickEvent(
        val photoId: String,
    ) : ListEvent()

    data class OnFavClickEvent(
        val photoId: String,
    ) : ListEvent()
}
