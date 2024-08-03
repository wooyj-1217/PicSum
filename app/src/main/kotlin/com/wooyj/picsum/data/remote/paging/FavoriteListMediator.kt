package com.wooyj.picsum.data.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.data.local.room.entity.toListPicSumEntity
import com.wooyj.picsum.domain.repository.LocalPicSumRepository
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class FavoriteListMediator
    @Inject
    constructor(
        private val remoteRepo: RemotePicSumRepository, // remote
        private val localRepo: LocalPicSumRepository, // db
        private val limit: Int,
    ) : RemoteMediator<Int, PicSumEntity>() {
        override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH

        override suspend fun load(
            loadType: LoadType,
            state: PagingState<Int, PicSumEntity>,
        ): MediatorResult {
            return try {
                val page =
                    when (loadType) {
                        LoadType.REFRESH -> {
                            // 처음 로드하거나 데이터를 갱신할 때, 초기 페이지를 설정
                            1
                        }
                        LoadType.PREPEND -> {
                            // 이전 페이지 로드할 때 사용하지 않는 경우
                            return MediatorResult.Success(endOfPaginationReached = true)
                        }
                        LoadType.APPEND -> {
                            // 다음 페이지를 로드할 때
                            val lastItem = state.lastItemOrNull()
                            Timber.d("state.lastItemOrNull: ${state.lastItemOrNull()}")
                            if (lastItem == null) {
                                return MediatorResult.Success(endOfPaginationReached = true)
                            } else {
                                if (lastItem.id.toInt() == 1084) {
                                    return MediatorResult.Success(endOfPaginationReached = true)
                                } else {
                                    val nextKeys = ((lastItem.id.toInt() + 1) / limit) + 1
                                    nextKeys
                                }
                            }
                        }
                    }

                Timber.d("loadType: $loadType, page: $page")

                val response =
                    remoteRepo
                        .getPicSumList(
                            page = page,
                            limit = state.config.pageSize,
                        )

                if (response != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (loadType == LoadType.REFRESH) {
                            localRepo.deleteAll()
                        }
                        localRepo.insert(response.toListPicSumEntity())
                    }
                } else {
                    MediatorResult.Error(Exception("response is null"))
                }
                MediatorResult.Success(
                    endOfPaginationReached = response.isEmpty(),
                )
            } catch (e: Exception) {
                Timber.e(e)
                MediatorResult.Error(e)
            }
        }
    }
