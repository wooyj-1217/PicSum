package com.wooyj.picsum.data.remote.dto

data class PicSumItemDTO(
    val id: String = "",
    val author: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val url: String = "",
    val download_url: String = "",
)
