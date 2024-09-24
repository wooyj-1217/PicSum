package com.wooyj.picsum.feature.detail.ui

import com.wooyj.picsum.ui.base.BaseEffect

interface DetailEffect : BaseEffect {
    data object NavigateToNext : DetailEffect

    data object NavigateToBefore : DetailEffect
}
