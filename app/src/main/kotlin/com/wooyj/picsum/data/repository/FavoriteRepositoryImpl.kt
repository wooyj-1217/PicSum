package com.wooyj.picsum.data.repository

import com.wooyj.picsum.data.local.room.dao.PicSumDAO
import com.wooyj.picsum.data.local.room.dao.PicSumDAOFlow
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl
    @Inject
    constructor(
        private val dao: PicSumDAO,
        private val flowDao: PicSumDAOFlow,
    ) : FavoriteRepository {
        override suspend fun getFavoriteList(): Flow<List<PicSumEntity>> = flowDao.getFavoriteList()

        override suspend fun addFavorite(entity: PicSumEntity) = dao.insert(entity)

        override suspend fun removeFavorite(entity: PicSumEntity) = dao.deleteById(entity.id.toInt())

        override suspend fun added(entity: PicSumEntity): Boolean = dao.getFavoriteCount(entity.id.toInt()) > 0
    }
