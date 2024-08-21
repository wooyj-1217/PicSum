package com.wooyj.picsum.data.remote.dto

import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import kotlinx.serialization.Serializable

@Serializable
data class PicSumItemDTO(
    val id: String = "",
    val author: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val url: String = "",
    val download_url: String = "",
)

fun PicSumItemDTO.toPicSumEntity() =
    PicSumEntity(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = download_url,
    )
