package com.wooyj.picsum.feature.favdetail.ui

import com.wooyj.picsum.ui.base.BaseEffect

interface FavDetailEffect : BaseEffect {
    data object NavigateToNext : FavDetailEffect

    data object NavigateToBefore : FavDetailEffect
}
