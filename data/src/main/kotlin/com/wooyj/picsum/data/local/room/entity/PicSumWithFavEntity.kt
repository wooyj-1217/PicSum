package com.wooyj.picsum.data.local.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.wooyj.picsum.domain.model.PicSumItemFavModel

@Entity
data class PicSumWithFavEntity(
    @Embedded val picSumEntity: PicSumEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val favoriteEntity: FavoriteEntity,
)

fun PicSumWithFavEntity.toPicSumItemFavModel() =
    PicSumItemFavModel(
        id = picSumEntity.id,
        author = picSumEntity.author,
        width = picSumEntity.width,
        height = picSumEntity.height,
        url = picSumEntity.url,
        downloadUrl = picSumEntity.downloadUrl,
        favorite = favoriteEntity.visible,
    )
