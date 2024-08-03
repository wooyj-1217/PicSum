package com.wooyj.picsum.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wooyj.picsum.data.remote.dto.PicSumItemDTO

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

fun List<PicSumItemDTO>.toListPicSumEntity() =
    map {
        PicSumEntity(
            id = it.id,
            author = it.author,
            width = it.width,
            height = it.height,
            url = it.url,
            downloadUrl = it.download_url,
            favorite = false,
        )
    }
