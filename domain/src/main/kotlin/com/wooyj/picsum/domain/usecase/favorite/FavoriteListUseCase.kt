package com.wooyj.picsum.domain.usecase.favorite

import androidx.paging.PagingData
import com.wooyj.picsum.domain.model.PicSumItemFavModel
import com.wooyj.picsum.domain.usecase.local.picsumfav.LocalPicSumFavListUseCase
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
        suspend operator fun invoke(): Flow<PagingData<PicSumItemFavModel>> =
            channelFlow {
                val picSumWithFavListFlow = picSumWithFavListUseCase()
                picSumWithFavListFlow.collectLatest { picSumWithFavList ->
                    val pagingData =
                        PagingData.from(
                            picSumWithFavList.map { entity ->
                                PicSumItemFavModel(
                                    id = entity.picSumEntity.id,
                                    author = entity.picSumEntity.author,
                                    width = entity.picSumEntity.width,
                                    height = entity.picSumEntity.height,
                                    url = entity.picSumEntity.url,
                                    downloadUrl = entity.picSumEntity.downloadUrl,
                                    favorite = true,
                                )
                            },
                        )
                    send(pagingData)
                }
            }
    }
