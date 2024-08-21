package com.wooyj.picsum.data.local.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class PicSumWithFavEntity(
    @Embedded val picSumEntity: PicSumEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
    )
    val favoriteEntity: FavoriteEntity,
)
