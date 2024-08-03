package com.wooyj.picsum.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
//    val favorite: Boolean, 이 요소는 필요가 없다. favorite이 true인 것만 저장할 것이기 때문.
)
