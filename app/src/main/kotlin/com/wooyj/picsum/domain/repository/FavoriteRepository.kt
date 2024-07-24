package com.wooyj.picsum.domain.repository

import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getFavoriteList(): Flow<List<PicSumEntity>>

    suspend fun added(entity: PicSumEntity): Boolean

    suspend fun addFavorite(entity: PicSumEntity)

    suspend fun removeFavorite(entity: PicSumEntity)
}
