package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.domain.model.PicSumItemFavModel
import kotlinx.coroutines.flow.Flow

interface LocalPicSumWithFavRepository {
    suspend fun getPicSumWithFavoriteList(): Flow<List<PicSumItemFavModel>>
}
