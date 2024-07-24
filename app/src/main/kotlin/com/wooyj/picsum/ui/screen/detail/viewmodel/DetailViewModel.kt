package com.wooyj.picsum.ui.screen.detail.viewmodel

import com.wooyj.picsum.ui.screen.detail.DetailEffect
import com.wooyj.picsum.ui.screen.detail.DetailEvent
import com.wooyj.picsum.ui.screen.detail.DetailUIState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

// DetailViewModel -> ListDetailViewModel / LikeViewModel
interface DetailViewModel {
    val uiState: StateFlow<DetailUIState>
    val effect: SharedFlow<DetailEffect>

    fun onEvent(event: DetailEvent)
}
