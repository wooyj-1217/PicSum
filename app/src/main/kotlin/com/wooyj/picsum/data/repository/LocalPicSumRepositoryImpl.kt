package com.wooyj.picsum.data.repository

import androidx.paging.PagingSource
import com.wooyj.picsum.data.local.room.dao.PicSumDAO
import com.wooyj.picsum.data.local.room.dao.PicSumDAOFlow
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.domain.repository.LocalPicSumRepository
import javax.inject.Inject

class LocalPicSumRepositoryImpl
    @Inject
    constructor(
        private val dao: PicSumDAO,
        private val flowDao: PicSumDAOFlow,
    ) : LocalPicSumRepository {
        override fun getFavoriteList(): PagingSource<Int, PicSumEntity> = flowDao.getFavoriteList()

        override suspend fun updateFavoriteById(entity: PicSumEntity) = dao.updateFavoriteById(entity.id, !entity.favorite)

        override suspend fun insert(list: List<PicSumEntity>) = dao.insert(list)

        override suspend fun deleteAll() = dao.deleteAll()
    }
