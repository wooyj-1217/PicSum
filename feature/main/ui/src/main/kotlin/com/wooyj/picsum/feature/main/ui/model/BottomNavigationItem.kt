package com.wooyj.picsum.feature.main.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.wooyj.picsum.feature.main.ui.R
import com.wooyj.picsum.feature.main.ui.navigation.Screen

enum class BottomNavigationItem(
    val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    List(
        title = R.string.list,
        icon = Icons.AutoMirrored.Filled.List,
        route = Screen.List.route,
    ),
    Favorite(
        title = R.string.favorite,
        icon = Icons.Default.Favorite,
        route = Screen.FavoriteList.route,
    ),
    Setting(
        title = R.string.setting,
        icon = Icons.Default.Settings,
        route = Screen.Setting.route,
    ),
}
