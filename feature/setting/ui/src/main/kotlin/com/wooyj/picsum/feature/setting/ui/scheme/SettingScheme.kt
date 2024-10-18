package com.wooyj.picsum.feature.setting.ui.scheme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.wooyj.picsum.ui.scheme.BottomNavigationScheme
import com.wooyj.picsum.ui.scheme.NavigationScheme

val settingScheme = NavigationScheme("setting")

val settingBottomNavigationScheme
    @Composable
    get() =
        BottomNavigationScheme(
            title = "Setting",
            icon = { Icon(imageVector = Icons.Outlined.Settings, contentDescription = "") },
            scheme = settingScheme,
        )
