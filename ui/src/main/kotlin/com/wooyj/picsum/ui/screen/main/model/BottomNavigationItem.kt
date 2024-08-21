package com.wooyj.picsum.ui.screen.main.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.wooyj.picsum.ui.navigation.Screen

enum class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    List(
        title = "리스트",
        icon = Icons.AutoMirrored.Filled.List,
        route = Screen.List.route,
    ),
    Favorite(
        title = "좋아요",
        icon = Icons.Default.Favorite,
        route = Screen.FavoriteList.route,
    ),
    Setting(
        title = "설정",
        icon = Icons.Default.Settings,
        route = Screen.Setting.route,
    ),
}
