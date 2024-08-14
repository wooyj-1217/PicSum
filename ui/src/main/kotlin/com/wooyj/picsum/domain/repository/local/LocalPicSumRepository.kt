package com.wooyj.picsum.domain.repository.local

import com.wooyj.picsum.data.local.room.entity.PicSumEntity

interface LocalPicSumRepository {
    suspend fun insert(list: List<PicSumEntity>)

    suspend fun deleteAll()

    suspend fun getPicSumListPaging(
        offset: Int,
        limit: Int,
    ): List<PicSumEntity>

    suspend fun getPicSumList(): List<PicSumEntity>
}
