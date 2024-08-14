package com.wooyj.picsum.data.repository.local

import com.wooyj.picsum.data.local.room.dao.FavoriteDAO
import com.wooyj.picsum.data.local.room.dao.FavoriteDAOFlow
import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalFavoriteRepositoryImpl
    @Inject
    constructor(
        private val dao: FavoriteDAO,
        private val flowDao: FavoriteDAOFlow,
    ) : LocalFavoriteRepository {
        override suspend fun getFavoriteItem(id: String): FavoriteEntity = dao.getFavoriteItem(id)

        override suspend fun addFavorite(entity: FavoriteEntity): Long = dao.insert(entity)

        override suspend fun removeFavorite(id: String): Int = dao.deleteById(id)

        override suspend fun updateFavorite(entity: FavoriteEntity): Int = dao.update(entity)

        override suspend fun added(id: String): Boolean = dao.getFavoriteCount(id.toInt()) > 0

        override suspend fun getFavoriteList(): Flow<List<FavoriteEntity>> = flowDao.getFavoriteList()

        override suspend fun getVisibleFavoriteList(): Flow<List<FavoriteEntity>> = flowDao.getVisibleFavoriteList()

        override suspend fun removeFavoriteNotVisible(): Int = dao.deleteNotVisible()
    }
