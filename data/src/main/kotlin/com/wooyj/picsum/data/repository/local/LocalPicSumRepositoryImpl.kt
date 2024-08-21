package com.wooyj.picsum.data.repository.local

import com.wooyj.picsum.data.local.room.dao.PicSumDAO
import com.wooyj.picsum.data.local.room.dao.PicSumDAOFlow
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.domain.repository.local.LocalPicSumRepository
import javax.inject.Inject

class LocalPicSumRepositoryImpl
    @Inject
    constructor(
        private val dao: PicSumDAO,
        private val flowDao: PicSumDAOFlow,
    ) : LocalPicSumRepository {
        override suspend fun insert(list: List<PicSumEntity>) = dao.insert(list)

        override suspend fun insert(entity: PicSumEntity): Long? = dao.insert(entity)

        override suspend fun deleteAll() = dao.deleteAll()

        override suspend fun getPicSumListPaging(
            offset: Int,
            limit: Int,
        ) = dao.getPicSumListPaging(
            offset = offset,
            limit = limit,
        )

        override suspend fun getPicSumList() = dao.getPicSumList()

        override suspend fun getPicSumItem(id: String): PicSumEntity? = dao.getPicSumItem(id)

        override suspend fun getPrevId(currentId: String): String? = dao.getPrevId(currentId)

        override suspend fun getNextId(currentId: String): String? = dao.getNextId(currentId)
    }
