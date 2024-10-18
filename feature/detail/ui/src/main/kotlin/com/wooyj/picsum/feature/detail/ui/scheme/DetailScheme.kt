package com.wooyj.picsum.feature.detail.ui.scheme

import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val detailScheme =
    NavigationScheme(
        route = "detail",
    )

val detailBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "Detail",
            icon = null,
            scheme = detailScheme,
        )
