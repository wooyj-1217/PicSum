package com.wooyj.picsum.feature.list.ui.scheme

import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val listScheme = NavigationScheme("list")

val listBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "List",
            icon = null,
            scheme = listScheme,
        )
