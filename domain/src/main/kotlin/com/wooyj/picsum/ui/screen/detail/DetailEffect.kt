package com.wooyj.picsum.ui.screen.detail

import com.wooyj.picsum.ui.base.BaseEffect

interface DetailEffect : BaseEffect {
    data object NavigateToNext : DetailEffect

    data object NavigateToBefore : DetailEffect
}
