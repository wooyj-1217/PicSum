package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface LocalFavoriteRepository {
    suspend fun getFavoriteItem(id: String): FavoriteEntity?

    suspend fun added(id: String): Boolean

    suspend fun addFavorite(entity: FavoriteEntity): Long

    suspend fun removeFavorite(id: String): Int

    suspend fun updateFavorite(entity: FavoriteEntity): Int

    suspend fun getFavoriteList(): Flow<List<FavoriteEntity>>

    suspend fun getVisibleFavoriteList(): Flow<List<FavoriteEntity>>

    suspend fun removeFavoriteNotVisible(): Int
}
