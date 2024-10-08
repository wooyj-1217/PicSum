package com.wooyj.picsum.data.repository.local

import kotlinx.coroutines.flow.Flow

interface LocalPicSumWithFavRepository {
    fun getPicSumWithFavoriteList(): Flow<List<com.wooyj.picsum.model.PicSumItemFavModel>>
}
