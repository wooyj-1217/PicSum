package com.wooyj.picsum.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wooyj.picsum.data.remote.dto.PicSumItemDTO
import com.wooyj.picsum.domain.repository.PicSumRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetPicSumListUseCase
    @Inject
    constructor(
        private val repository: PicSumRepository,
    ) {
        operator fun invoke(limit: Int): Flow<PagingData<PicSumItemDTO>> {
            val pagerFlow =
                Pager(
                    PagingConfig(
                        pageSize = limit,
                        enablePlaceholders = false,
                    ),
                    pagingSourceFactory = { repository.getPicSumPagingSource() },
                ).flow
            return pagerFlow
        }
    }
