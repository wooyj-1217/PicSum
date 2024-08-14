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
    suspend fun insert(data: FavoriteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<FavoriteEntity>)

    @Query("SELECT * FROM favorite")
    suspend fun getFavoriteList(): List<FavoriteEntity>

    @Update
    suspend fun update(data: FavoriteEntity): Int

    @Update
    suspend fun update(list: List<FavoriteEntity>): Int

    @Delete
    suspend fun delete(data: FavoriteEntity)

    @Delete
    suspend fun delete(list: List<FavoriteEntity>)

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun deleteById(id: String): Int

    @Query("DELETE FROM favorite")
    suspend fun deleteAll()

    @Query("DELETE FROM favorite WHERE visible = 0")
    suspend fun deleteNotVisible(): Int

    @Query("SELECT Count(*) FROM favorite WHERE id = :id")
    suspend fun getFavoriteCount(id: Int): Int

    @Query("SELECT * FROM favorite WHERE id = :id")
    suspend fun getFavoriteItem(id: String): FavoriteEntity
}
