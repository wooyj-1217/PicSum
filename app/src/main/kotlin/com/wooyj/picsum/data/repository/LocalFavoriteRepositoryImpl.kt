package com.wooyj.picsum.data.repository

import com.wooyj.picsum.data.local.room.dao.FavoriteDAO
import com.wooyj.picsum.data.local.room.dao.FavoriteDAOFlow
import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import com.wooyj.picsum.domain.repository.LocalFavoriteRepository
import javax.inject.Inject

class LocalFavoriteRepositoryImpl
    @Inject
    constructor(
        private val dao: FavoriteDAO,
        private val flowDao: FavoriteDAOFlow,
    ) : LocalFavoriteRepository {
        override suspend fun addFavorite(entity: FavoriteEntity) = dao.insert(entity)

        override suspend fun removeFavorite(entity: FavoriteEntity) = dao.deleteById(entity.id.toInt())

        override suspend fun added(id: String): Boolean = dao.getFavoriteCount(id.toInt()) > 0
    }
