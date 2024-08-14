package com.wooyj.picsum.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wooyj.picsum.data.local.room.dao.FavoriteDAO
import com.wooyj.picsum.data.local.room.dao.FavoriteDAOFlow
import com.wooyj.picsum.data.local.room.dao.PicSumDAO
import com.wooyj.picsum.data.local.room.dao.PicSumDAOFlow
import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import com.wooyj.picsum.data.local.room.entity.PicSumEntity

private const val DATABASE_VERSION = 2

@Database(version = DATABASE_VERSION, entities = [PicSumEntity::class, FavoriteEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun picSumDao(): PicSumDAO

    abstract fun picSumDaoFlow(): PicSumDAOFlow

    abstract fun favoriteDao(): FavoriteDAO

    abstract fun favoriteDAOFlow(): FavoriteDAOFlow
}
