package com.wooyj.picsum.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PicSumDAOFlow {
    @Query("SELECT * FROM pic_sum")
    fun getFavoriteList(): Flow<List<PicSumEntity>>
}
