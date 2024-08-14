package com.wooyj.picsum.domain.usecase.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.domain.repository.paging.PagingPicSumRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class PicSumListPagingUseCase
    @Inject
    constructor(
        private val pagingRepo: PagingPicSumRepository,
    ) {
        operator fun invoke(limit: Int): Flow<PagingData<PicSumEntity>> =
            Pager(
                PagingConfig(
                    pageSize = limit,
                    enablePlaceholders = false,
                ),
                pagingSourceFactory = {
                    pagingRepo.getPicSumPagingSource()
                },
            ).flow
    }
