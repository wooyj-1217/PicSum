package com.wooyj.picsum.ui.screen.list

interface ListEffect {
    data class NavigateToDetail(
        val photoId: Int,
    ) : ListEffect
}
