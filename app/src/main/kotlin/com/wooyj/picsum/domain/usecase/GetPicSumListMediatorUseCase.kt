package com.wooyj.picsum.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.data.remote.paging.FavoriteListMediator
import com.wooyj.picsum.domain.repository.LocalFavoriteRepository
import com.wooyj.picsum.domain.repository.LocalPicSumRepository
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetPicSumListMediatorUseCase
    @Inject
    constructor(
        private val remoteRepo: RemotePicSumRepository,
        private val localRepo: LocalPicSumRepository,
        private val favoriteRepository: LocalFavoriteRepository,
        private val isFavoriteItemUseCase: IsFavoriteItemUseCase,
    ) {
        @OptIn(ExperimentalPagingApi::class)
        operator fun invoke(
            limit: Int,
            scope: CoroutineScope,
        ): Flow<PagingData<PicSumEntity>> {
            val pager =
                Pager(
                    config = PagingConfig(pageSize = limit, initialLoadSize = limit),
                    remoteMediator = FavoriteListMediator(remoteRepo, localRepo, limit),
                    pagingSourceFactory = { localRepo.getFavoriteList() },
                )

            return pager.flow
                .cachedIn(scope)
                .map {
                    it.map { entity ->
                        entity.copy(favorite = isFavoriteItemUseCase(entity.id))
                    }
                }
        }
    }
