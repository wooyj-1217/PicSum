package com.wooyj.picsum.ui.screen.main.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val selected: Boolean = false,
)
