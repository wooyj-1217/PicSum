package com.wooyj.picsum.domain.model

data class ItemWithIdModel(
    val prevId: String?,
    val nextId: String?,
    val item: PicSumItemFavModel?,
)
