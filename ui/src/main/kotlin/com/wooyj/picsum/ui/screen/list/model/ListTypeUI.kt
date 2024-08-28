package com.wooyj.picsum.ui.screen.list.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import com.wooyj.picsum.domain.model.PicSum
import com.wooyj.picsum.domain.model.PicSumItemFavModel

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

fun PicSumItemFavModel.toListTypeUI() =
    ListTypeUI(
        photoId = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
        favorite = favorite,
    )

fun PicSum.toListTypeUI() =
    ListTypeUI(
        photoId = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
    )

fun ListTypeUI.toPicSum() =
    PicSum(
        id = photoId.toString(),
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
    )
