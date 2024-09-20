package com.wooyj.picsum.feature.list.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import com.wooyj.picsum.model.PicSum
import com.wooyj.picsum.model.PicSumItemFavModel

data class ListTypeUI(
    val photoId: String,
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

fun com.wooyj.picsum.model.PicSumItemFavModel.toListTypeUI() =
    ListTypeUI(
        photoId = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
        favorite = favorite,
    )

fun com.wooyj.picsum.model.PicSum.toListTypeUI() =
    ListTypeUI(
        photoId = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
    )

fun ListTypeUI.toPicSum() =
    com.wooyj.picsum.model.PicSum(
        id = photoId.toString(),
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
    )
