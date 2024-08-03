package com.wooyj.picsum.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wooyj.picsum.data.remote.dto.PicSumItemDTO
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class PicSumListUseCase
    @Inject
    constructor(
        private val repository: RemotePicSumRepository,
    ) {
        operator fun invoke(
            limit: Int,
            scope: CoroutineScope,
        ): Flow<PagingData<PicSumItemDTO>> =
            Pager(
                PagingConfig(
                    pageSize = limit,
                    enablePlaceholders = false,
                ),
                pagingSourceFactory = { repository.getPicSumPagingSource() },
            ).flow.cachedIn(scope)
    }
