package com.wooyj.picsum.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wooyj.picsum.model.Favorite

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val id: String,
    val visible: Boolean = true,
)
// {
//    fun toFavorite() =
//        Favorite(
//            id = id,
//            visible = visible,
//        )
//
//    companion object {
//        fun fromFavorite(favorite: Favorite) =
//            FavoriteEntity(
//                id = favorite.id,
//                visible = favorite.visible,
//            )
//    }
// }

fun FavoriteEntity.toFavorite() =
    Favorite(
        id = id,
        visible = visible,
    )

fun Favorite.toEntity() =
    FavoriteEntity(
        id = id,
        visible = visible,
    )
