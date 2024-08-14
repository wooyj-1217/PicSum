package com.wooyj.picsum.ui.navigation

sealed class Screen(
    val route: String,
) {
    data object List : Screen(route = "list")

    data object Detail : Screen(route = "detail/{photoId}") {
        fun setPhotoId(photoId: String): String = "detail/$photoId"
    }

    data object Favorite : Screen(route = "favorite")

    data object Setting : Screen(route = "setting")
}
