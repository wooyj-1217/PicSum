package com.wooyj.picsum.feature.favdetail.ui.scheme

import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val favDetailScheme = NavigationScheme("favDetail")

val favDetailBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "FavDetail",
            icon = null,
            route = favDetailScheme,
        )
