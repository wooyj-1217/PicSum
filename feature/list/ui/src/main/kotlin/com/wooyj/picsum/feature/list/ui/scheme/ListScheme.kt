package com.wooyj.picsum.feature.list.ui.scheme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val listScheme = NavigationScheme("list")

val listBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "List",
            icon = { Icon(imageVector = Icons.AutoMirrored.Rounded.List, contentDescription = "") },
            scheme = listScheme,
        )
