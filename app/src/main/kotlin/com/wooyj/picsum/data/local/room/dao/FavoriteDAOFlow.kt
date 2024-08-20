package com.wooyj.picsum.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDAOFlow {
    @Query("SELECT * FROM favorite")
    fun getFavoriteList(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE visible = 1")
    fun getVisibleFavoriteList(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE visible = 1")
    fun getVisibleFavoriteListPaging(): PagingSource<Int, FavoriteEntity>
}
