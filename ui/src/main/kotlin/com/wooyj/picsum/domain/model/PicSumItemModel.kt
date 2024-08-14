package com.wooyj.picsum.domain.model

import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.data.remote.dto.PicSumItemDTO

data class PicSumItemFavModel(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val favorite: Boolean = false,
)

fun PicSumItemDTO.toPicSumItemFavModel(isFavorite: Boolean) =
    PicSumItemFavModel(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = download_url,
        favorite = isFavorite,
    )

fun PicSumEntity.toPicSumItemFavModel(isFavorite: Boolean) =
    PicSumItemFavModel(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
        favorite = isFavorite,
    )
