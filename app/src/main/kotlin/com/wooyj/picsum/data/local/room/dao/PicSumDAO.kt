package com.wooyj.picsum.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.wooyj.picsum.data.local.room.entity.PicSumEntity

@Dao
interface PicSumDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: PicSumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<PicSumEntity>)

    @Query("SELECT * FROM pic_sum")
    suspend fun getPicSumList(): List<PicSumEntity>

    @Query("SELECT * FROM pic_sum Limit :limit OFFSET :offset")
    suspend fun getPicSumListPaging(
        offset: Int,
        limit: Int,
    ): List<PicSumEntity>

    @Update
    suspend fun update(data: PicSumEntity)

    @Update
    suspend fun update(list: List<PicSumEntity>)

    @Delete
    suspend fun delete(data: PicSumEntity)

    @Delete
    suspend fun delete(list: List<PicSumEntity>)

    @Query("DELETE FROM pic_sum WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM pic_sum")
    suspend fun deleteAll()

    @Query("SELECT Count(*) FROM pic_sum WHERE id = :id")
    suspend fun getFavoriteCount(id: Int): Int
}
