package com.wooyj.picsum.data.repository.local

import com.wooyj.picsum.data.local.room.dao.FavoriteDAO
import com.wooyj.picsum.data.local.room.dao.FavoriteDAOFlow
import com.wooyj.picsum.data.local.room.entity.toEntity
import com.wooyj.picsum.data.local.room.entity.toFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalFavoriteRepositoryImpl
    @Inject
    constructor(
        private val dao: FavoriteDAO,
        private val flowDao: FavoriteDAOFlow,
    ) : LocalFavoriteRepository {
        override suspend fun getFavoriteItem(id: String): com.wooyj.picsum.model.Favorite? =
            dao
                .getFavoriteItem(id)
                ?.toFavorite()

        override suspend fun addFavorite(entity: com.wooyj.picsum.model.Favorite): Long = dao.insert(entity.toEntity())

        override suspend fun removeFavorite(id: String): Int = dao.deleteById(id)

        override suspend fun updateFavorite(entity: com.wooyj.picsum.model.Favorite): Int = dao.update(entity.toEntity())

        override suspend fun added(id: String): Boolean = dao.getFavoriteCount(id.toInt()) > 0

        override fun getFavoriteList(): Flow<List<com.wooyj.picsum.model.Favorite>> =
            flowDao
                .getFavoriteList()
                .map { it.map { it.toFavorite() } }

        override fun getVisibleFavoriteList(): Flow<List<com.wooyj.picsum.model.Favorite>> =
            flowDao
                .getVisibleFavoriteList()
                .map { it.map { it.toFavorite() } }

        override suspend fun removeFavoriteNotVisible(): Int = dao.deleteNotVisible()

        override suspend fun getPrevId(currentId: String): String? = dao.getPrevId(currentId)

        override suspend fun getNextId(currentId: String): String? = dao.getNextId(currentId)
    }
