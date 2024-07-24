package com.wooyj.picsum.ui.screen.detail

sealed class DetailEvent {
    data object OnNextClick : DetailEvent()

    data object OnBeforeClick : DetailEvent()

    data object OnFavoriteClick : DetailEvent()
}
