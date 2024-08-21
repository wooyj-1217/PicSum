package com.wooyj.picsum.ui.screen.main

import com.wooyj.picsum.ui.base.BaseEffect
import com.wooyj.picsum.ui.navigation.Screen

sealed interface MainEffect : BaseEffect {
    val screen: Screen

    data object NavigateToList : MainEffect {
        override val screen: Screen
            get() = Screen.List
    }

    data object NavigateToFavorite : MainEffect {
        override val screen: Screen
            get() = Screen.FavoriteList
    }

    data object NavigateToSetting : MainEffect {
        override val screen: Screen
            get() = Screen.Setting
    }
}

// sealed interface MainEffect2 {
//    data class Navigate(
//        val screen: Screen,
//    ) : MainEffect2
// }
