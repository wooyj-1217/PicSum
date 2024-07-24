package com.wooyj.picsum.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wooyj.picsum.data.local.room.dao.PicSumDAO
import com.wooyj.picsum.data.local.room.dao.PicSumDAOFlow
import com.wooyj.picsum.data.local.room.entity.PicSumEntity

private const val DATABASE_VERSION = 1

@Database(version = DATABASE_VERSION, entities = [PicSumEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun picSumDao(): PicSumDAO

    abstract fun picSumDaoFlow(): PicSumDAOFlow
}
