package com.wooyj.picsum.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import com.wooyj.picsum.model.ItemId

data class ImageWithFavoriteUIState(
    val itemId: ItemId,
    val url: String,
    val favorite: Boolean = false,
)

fun ImageWithFavoriteUIState.getIcon() =
    if (favorite) {
        Icons.Filled.Favorite
    } else {
        Icons.Outlined.FavoriteBorder
    }
