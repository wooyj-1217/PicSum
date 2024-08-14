package com.wooyj.picsum.domain.model

data class ItemWithIdModel(
    val beforeId: String?,
    val nextId: String?,
    val item: PicSumItemFavModel?,
)

data class DetailModel(
    val beforeId: String?,
    val nextId: String?,
    val currentId: String?,
    val currentItem: PicSumItemFavModel?,
)
