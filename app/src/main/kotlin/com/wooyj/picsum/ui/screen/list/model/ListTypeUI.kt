package com.wooyj.picsum.ui.screen.list.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.data.remote.dto.PicSumItemDTO

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
    when (favorite) {
        true -> {
            Icons.Filled.Favorite
        }

        false -> {
            Icons.Outlined.FavoriteBorder
        }
    }

fun PicSumItemDTO.toListTypeUI(favorite: Boolean): ListTypeUI =
    ListTypeUI(
        photoId = id.toInt(),
        url = url,
        favorite = favorite,
        author = author,
        width = width,
        height = height,
        downloadUrl = download_url,
    )

fun ListTypeUI.toPicSumEntity(): PicSumEntity =
    PicSumEntity(
        id = photoId.toString(),
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
        favorite = favorite,
    )
