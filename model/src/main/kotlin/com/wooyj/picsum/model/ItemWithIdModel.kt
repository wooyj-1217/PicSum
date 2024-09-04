package com.wooyj.picsum.model

data class ItemWithIdModel(
    val prevId: String?,
    val nextId: String?,
    val item: PicSumItemFavModel?,
)
