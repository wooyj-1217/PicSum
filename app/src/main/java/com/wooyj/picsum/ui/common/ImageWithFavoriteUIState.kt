package com.wooyj.picsum.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite

data class ImageWithFavoriteUIState(
    val photoId: Int,
    val url: String,
    val favorite: Boolean = false,
)

fun ImageWithFavoriteUIState.getIcon() =
    when (favorite) {
        true -> {
            Icons.Filled.Favorite
        }

        false -> {
            Icons.Outlined.Favorite
        }
    }
