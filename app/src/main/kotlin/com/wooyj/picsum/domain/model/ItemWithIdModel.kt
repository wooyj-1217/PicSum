package com.wooyj.picsum.domain.model

data class ItemWithIdModel(
    val beforeId: String?,
    val nextId: String?,
    val item: PicSumItemFavModel?,
)
