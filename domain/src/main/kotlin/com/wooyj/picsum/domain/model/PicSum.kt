package com.wooyj.picsum.domain.model

data class PicSum(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val favorite: Boolean = false,
)

fun PicSum.toPicSumItemFavModel(isFavorite: Boolean) =
    PicSumItemFavModel(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
        favorite = isFavorite,
    )
