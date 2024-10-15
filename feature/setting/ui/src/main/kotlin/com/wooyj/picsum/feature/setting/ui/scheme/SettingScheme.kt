package com.wooyj.picsum.feature.setting.ui.scheme

import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val settingScheme = NavigationScheme("setting")

val settingBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "Setting",
            icon = null,
            scheme = settingScheme,
        )
