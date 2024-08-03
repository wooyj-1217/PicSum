package com.wooyj.picsum.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wooyj.picsum.data.local.room.entity.FavoriteEntity

@Dao
interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<FavoriteEntity>)

    @Query("SELECT * FROM favorite")
    suspend fun getPicSumList(): List<FavoriteEntity>

    @Update
    suspend fun update(data: FavoriteEntity)

    @Update
    suspend fun update(list: List<FavoriteEntity>)

    @Delete
    suspend fun delete(data: FavoriteEntity)

    @Delete
    suspend fun delete(list: List<FavoriteEntity>)

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM favorite")
    suspend fun deleteAll()

    @Query("SELECT Count(*) FROM favorite WHERE id = :id")
    suspend fun getFavoriteCount(id: Int): Int
}
