package com.wooyj.picsum.ui.screen.list.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import com.wooyj.picsum.data.local.room.entity.PicSumEntity

data class ListTypeUI(
    val photoId: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val favorite: Boolean = false,
)

@Composable
fun ListTypeUI.getIcon() =
    if (favorite) {
        Icons.Filled.Favorite
    } else {
        Icons.Outlined.FavoriteBorder
    }

fun PicSumEntity.toListTypeUI() =
    ListTypeUI(
        photoId = id.toInt(),
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
        favorite = favorite,
    )

fun ListTypeUI.toPicSumEntity() =
    PicSumEntity(
        id = photoId.toString(),
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
        favorite = favorite,
    )
