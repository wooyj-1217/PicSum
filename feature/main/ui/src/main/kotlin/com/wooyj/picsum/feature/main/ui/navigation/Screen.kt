package com.wooyj.picsum.feature.main.ui.navigation

sealed class Screen(
    val route: String,
) {
    data object List : Screen(route = "list")

    data object Detail : Screen(route = "detail/{photoId}") {
        fun setPhotoId(photoId: String): String = "detail/$photoId"
    }

    data object FavoriteList : Screen(route = "favorite/list")

    data object FavoriteDetail : Screen(route = "favorite/detail/{photoId}") {
        fun setPhotoId(photoId: String): String = "favorite/detail/$photoId"
    }

    data object Setting : Screen(route = "setting")
}
