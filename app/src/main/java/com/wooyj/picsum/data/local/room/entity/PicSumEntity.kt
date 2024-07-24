package com.wooyj.picsum.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pic_sum")
data class PicSumEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val favorite: Boolean,
)
