package com.wooyj.picsum.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.wooyj.picsum.data.local.room.entity.PicSumEntity

@Dao
interface PicSumDAOFlow {
    @Query("SELECT * FROM pic_sum")
    fun getFavoriteList(): PagingSource<Int, PicSumEntity>
}
