package com.wooyj.picsum.feature.favorite.ui.scheme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val favoriteScheme =
    NavigationScheme(
        route = "favorite",
    )

val favoriteBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "Favorite",
            icon = { Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "") },
            scheme = favoriteScheme,
        )
