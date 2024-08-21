package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface LocalFavoriteRepository {
    suspend fun getFavoriteItem(id: String): Favorite?

    suspend fun added(id: String): Boolean

    suspend fun addFavorite(entity: Favorite): Long

    suspend fun removeFavorite(id: String): Int

    suspend fun updateFavorite(entity: Favorite): Int

    fun getFavoriteList(): Flow<List<Favorite>>

    fun getVisibleFavoriteList(): Flow<List<Favorite>>

    suspend fun removeFavoriteNotVisible(): Int

    suspend fun getPrevId(currentId: String): String?

    suspend fun getNextId(currentId: String): String?
}
