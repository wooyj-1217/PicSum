package com.wooyj.picsum.feature.list.ui

import com.wooyj.picsum.ui.base.BaseEffect

interface ListEffect : BaseEffect {
    data class NavigateToDetail(
        val photoId: String,
    ) : ListEffect
}
