package com.wooyj.picsum.domain.usecase.favorite

import androidx.paging.PagingData
import com.wooyj.picsum.domain.usecase.local.picsumfav.LocalPicSumFavListUseCase
import com.wooyj.picsum.model.PicSumItemFavModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@Reusable
class FavoriteListUseCase
    @Inject
    constructor(
        private val picSumWithFavListUseCase: LocalPicSumFavListUseCase,
    ) {
        suspend operator fun invoke(): Flow<PagingData<com.wooyj.picsum.model.PicSumItemFavModel>> =
            channelFlow {
                val picSumWithFavListFlow = picSumWithFavListUseCase()
                picSumWithFavListFlow.collectLatest { picSumWithFavList ->
                    val pagingData =
                        PagingData.from(
                            picSumWithFavList.map { entity ->
                                com.wooyj.picsum.model.PicSumItemFavModel(
                                    id = entity.id,
                                    author = entity.author,
                                    width = entity.width,
                                    height = entity.height,
                                    url = entity.url,
                                    downloadUrl = entity.downloadUrl,
                                    favorite = true,
                                )
                            },
                        )
                    send(pagingData)
                }
            }
    }
