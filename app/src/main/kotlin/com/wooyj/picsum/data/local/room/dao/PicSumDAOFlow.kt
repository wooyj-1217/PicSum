package com.wooyj.picsum.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PicSumDAOFlow {
    @Query("SELECT * FROM pic_sum")
    fun getPicSumPagingSource(): PagingSource<Int, PicSumEntity>

    @Query("SELECT * FROM pic_sum")
    fun getPicSumListFlow(): Flow<List<PicSumEntity>>
}
