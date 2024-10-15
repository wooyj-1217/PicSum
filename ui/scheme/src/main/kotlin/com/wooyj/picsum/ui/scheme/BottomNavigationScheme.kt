package com.wooyj.picsum.ui.scheme

import androidx.compose.runtime.Composable

data class BottomNavigationScheme(
    val title: String,
    val icon: (@Composable () -> Unit)? = null,
    val scheme: NavigationScheme,
)
