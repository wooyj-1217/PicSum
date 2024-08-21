package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.data.local.room.entity.PicSumWithFavEntity
import kotlinx.coroutines.flow.Flow

interface LocalPicSumWithFavRepository {
    suspend fun getPicSumWithFavoriteList(): Flow<List<PicSumWithFavEntity>>
}
