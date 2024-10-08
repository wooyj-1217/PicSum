package com.wooyj.picsum.feature.favorite.ui.scheme

import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val favoriteScheme = NavigationScheme("favorite")

val favoriteBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "Favorite",
            icon = null,
            route = favoriteScheme,
        )
