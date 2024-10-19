package com.wooyj.picsum.ui

import com.wooyj.picsum.feature.favorite.ui.scheme.favoriteScheme
import com.wooyj.picsum.feature.list.ui.scheme.listScheme
import com.wooyj.picsum.feature.setting.ui.scheme.testSettingScheme
import com.wooyj.picsum.ui.base.BaseEffect
import com.wooyj.picsum.ui.scheme.NavigationScheme

sealed interface MainEffect : BaseEffect {
    val scheme: NavigationScheme

    data object NavigateToList : MainEffect {
        override val scheme: NavigationScheme
            get() = listScheme
    }

    data object NavigateToFavorite : MainEffect {
        override val scheme: NavigationScheme
            get() = favoriteScheme
    }

    data object NavigateToSetting : MainEffect {
        override val scheme: NavigationScheme
            get() = testSettingScheme
    }
}

// sealed interface MainEffect2 {
//    data class Navigate(
//        val screen: Screen,
//    ) : MainEffect2
// }
