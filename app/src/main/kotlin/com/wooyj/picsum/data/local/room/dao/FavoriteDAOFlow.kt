package com.wooyj.picsum.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDAOFlow {
    @Query("SELECT * FROM favorite")
    fun getFavoriteList(): Flow<List<FavoriteEntity>>
}
