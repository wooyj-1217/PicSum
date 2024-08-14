package com.wooyj.picsum.ui.screen.list

import com.wooyj.picsum.ui.base.BaseEffect

interface ListEffect : BaseEffect {
    data class NavigateToDetail(
        val photoId: String,
    ) : ListEffect
}
