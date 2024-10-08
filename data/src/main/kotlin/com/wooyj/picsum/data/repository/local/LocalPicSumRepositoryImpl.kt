package com.wooyj.picsum.data.repository.local

import com.wooyj.picsum.data.local.room.dao.PicSumDAO
import com.wooyj.picsum.data.local.room.dao.PicSumDAOFlow
import com.wooyj.picsum.data.local.room.entity.toPicSum
import com.wooyj.picsum.data.local.room.entity.toPicSumEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalPicSumRepositoryImpl
    @Inject
    constructor(
        private val dao: PicSumDAO,
        private val flowDao: PicSumDAOFlow,
    ) : LocalPicSumRepository {
        override suspend fun insert(list: List<com.wooyj.picsum.model.PicSum>) = dao.insert(list.map { it.toPicSumEntity() })

        override suspend fun insert(entity: com.wooyj.picsum.model.PicSum): Long? = dao.insert(entity.toPicSumEntity())

        override suspend fun deleteAll() = dao.deleteAll()

        override suspend fun getPicSumListPaging(
            offset: Int,
            limit: Int,
        ) = dao
            .getPicSumListPaging(
                offset = offset,
                limit = limit,
            ).map { it.toPicSum() }

        override suspend fun getPicSumList() = dao.getPicSumList().map { it.toPicSum() }

        override suspend fun getPicSumItem(id: String): com.wooyj.picsum.model.PicSum? = dao.getPicSumItem(id)?.toPicSum()

        override fun getPrevId(currentId: String): Flow<String?> = dao.getPrevId(currentId)

        override fun getNextId(currentId: String): Flow<String?> = dao.getNextId(currentId)
    }
