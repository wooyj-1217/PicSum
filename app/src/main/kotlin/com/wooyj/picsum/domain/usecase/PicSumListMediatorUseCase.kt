package com.wooyj.picsum.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.data.remote.paging.FavoriteListMediator
import com.wooyj.picsum.domain.repository.LocalPicSumRepository
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class PicSumListMediatorUseCase
    @Inject
    constructor(
        private val remoteRepo: RemotePicSumRepository,
        private val localRepo: LocalPicSumRepository,
    ) {
        @OptIn(ExperimentalPagingApi::class)
        operator fun invoke(
            limit: Int,
            scope: CoroutineScope,
        ): Flow<PagingData<PicSumEntity>> {
            val pager =
                Pager(
                    config = PagingConfig(pageSize = limit, initialLoadSize = 30),
                    remoteMediator = FavoriteListMediator(remoteRepo, localRepo, limit),
                    pagingSourceFactory = { localRepo.getFavoriteList() },
                )
            return pager.flow.cachedIn(scope)
        }
    }
