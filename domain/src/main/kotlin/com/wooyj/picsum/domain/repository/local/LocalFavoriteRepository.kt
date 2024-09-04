package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.model.Favorite
import kotlinx.coroutines.flow.Flow

interface LocalFavoriteRepository {
    suspend fun getFavoriteItem(id: String): com.wooyj.picsum.model.Favorite?

    suspend fun added(id: String): Boolean

    suspend fun addFavorite(entity: com.wooyj.picsum.model.Favorite): Long

    suspend fun removeFavorite(id: String): Int

    suspend fun updateFavorite(entity: com.wooyj.picsum.model.Favorite): Int

    fun getFavoriteList(): Flow<List<com.wooyj.picsum.model.Favorite>>

    fun getVisibleFavoriteList(): Flow<List<com.wooyj.picsum.model.Favorite>>

    suspend fun removeFavoriteNotVisible(): Int

    suspend fun getPrevId(currentId: String): String?

    suspend fun getNextId(currentId: String): String?
}
