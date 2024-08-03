package com.wooyj.picsum.domain.repository

import androidx.paging.PagingSource
import com.wooyj.picsum.data.local.room.entity.PicSumEntity

interface LocalPicSumRepository {
    fun getFavoriteList(): PagingSource<Int, PicSumEntity>

    suspend fun updateFavoriteById(entity: PicSumEntity)

    suspend fun insert(list: List<PicSumEntity>)

    suspend fun deleteAll()
}
