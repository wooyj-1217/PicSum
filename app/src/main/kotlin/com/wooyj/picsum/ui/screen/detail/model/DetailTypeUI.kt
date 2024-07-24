package com.wooyj.picsum.ui.screen.detail.model

data class DetailTypeUI(
    val photoId: Int,
    val url: String,
    val favorite: Boolean,
)

// max : 1084, min :0
fun DetailTypeUI.beforeButtonVisible() = photoId > -1

fun DetailTypeUI.nextButtonVisible() = photoId < 1085
