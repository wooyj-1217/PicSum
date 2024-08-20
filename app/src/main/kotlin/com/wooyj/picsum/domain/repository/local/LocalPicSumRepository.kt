package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.data.local.room.entity.PicSumEntity

interface LocalPicSumRepository {
    suspend fun insert(list: List<PicSumEntity>)

    suspend fun insert(entity: PicSumEntity): Long?

    suspend fun deleteAll()

    suspend fun getPicSumListPaging(
        offset: Int,
        limit: Int,
    ): List<PicSumEntity>

    suspend fun getPicSumList(): List<PicSumEntity>

    suspend fun getPicSumItem(id: String): PicSumEntity?

    suspend fun getPrevId(currentId: String): String?

    suspend fun getNextId(currentId: String): String?
}
