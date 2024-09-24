package com.wooyj.picsum.feature.favdetail.ui

import com.wooyj.picsum.ui.base.BaseEvent

sealed class FavDetailEvent : BaseEvent {
    data class OnNextClick(
        val photoId: String,
    ) : FavDetailEvent()

    data class OnBeforeClick(
        val photoId: String,
    ) : FavDetailEvent()

    data class OnFavoriteClick(
        val photoId: String,
    ) : FavDetailEvent()
}
