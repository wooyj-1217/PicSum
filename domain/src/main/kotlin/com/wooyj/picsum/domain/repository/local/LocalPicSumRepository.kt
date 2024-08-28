package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.domain.model.PicSum

interface LocalPicSumRepository {
    suspend fun insert(list: List<PicSum>)

    suspend fun insert(entity: PicSum): Long?

    suspend fun deleteAll()

    suspend fun getPicSumListPaging(
        offset: Int,
        limit: Int,
    ): List<PicSum>

    suspend fun getPicSumList(): List<PicSum>

    suspend fun getPicSumItem(id: String): PicSum?

    suspend fun getPrevId(currentId: String): String?

    suspend fun getNextId(currentId: String): String?
}
