package com.wooyj.picsum.domain.repository

import com.wooyj.picsum.data.local.room.entity.FavoriteEntity

interface LocalFavoriteRepository {
    suspend fun added(id: String): Boolean

    suspend fun addFavorite(entity: FavoriteEntity)

    suspend fun removeFavorite(entity: FavoriteEntity)
}
