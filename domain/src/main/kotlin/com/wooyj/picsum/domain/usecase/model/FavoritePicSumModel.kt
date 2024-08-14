package com.wooyj.picsum.domain.usecase.model

data class FavoritePicSumModel(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val isFavorite: Boolean,
)
