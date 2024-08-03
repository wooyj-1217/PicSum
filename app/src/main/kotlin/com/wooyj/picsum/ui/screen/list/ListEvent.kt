package com.wooyj.picsum.ui.screen.list

import com.wooyj.picsum.ui.base.BaseEvent
import com.wooyj.picsum.ui.screen.list.model.ListTypeUI

sealed class ListEvent : BaseEvent {
    data class OnItemClickEvent(
        val photoId: Int,
    ) : ListEvent()

    data class OnFavClickEvent(
        val ui: ListTypeUI,
    ) : ListEvent()
}
