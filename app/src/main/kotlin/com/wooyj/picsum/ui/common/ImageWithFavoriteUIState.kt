package com.wooyj.picsum.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder

data class ImageWithFavoriteUIState(
    val photoId: String,
    val url: String,
    val favorite: Boolean = false,
)

fun ImageWithFavoriteUIState.getIcon() =
    if (favorite) {
        Icons.Filled.Favorite
    } else {
        Icons.Outlined.FavoriteBorder
    }
