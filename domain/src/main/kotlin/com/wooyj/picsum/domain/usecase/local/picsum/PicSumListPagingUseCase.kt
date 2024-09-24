package com.wooyj.picsum.domain.usecase.local.picsum

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

fun interface PicSumListPagingUseCase {
    operator fun invoke(limit: Int): Flow<PagingData<com.wooyj.picsum.model.PicSum>>
}
