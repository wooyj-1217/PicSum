package com.wooyj.picsum.domain.usecase.favorite

import androidx.paging.PagingData
import com.wooyj.picsum.domain.model.PicSumItemFavModel
import com.wooyj.picsum.domain.usecase.local.favorite.FavoriteVisibleListUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalPicSumListUseCase
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber
import javax.inject.Inject

@Reusable
class FavoriteListUseCase
    @Inject
    constructor(
        private val favoriteListUseCase: FavoriteVisibleListUseCase,
        private val picSumListUseCase: LocalPicSumListUseCase,
    ) {
        suspend operator fun invoke(): Flow<PagingData<PicSumItemFavModel>> =
            channelFlow {
                val favList = favoriteListUseCase() // Flow<List<FavoriteEntity>>
                val picSumList = picSumListUseCase() // List<PicSumEntity>

                favList.collectLatest { favList ->
                    val favIdList = favList.map { it.id }
                    Timber.d("favIdList: $favIdList")

                    val filteredPicSumList =
                        picSumList
                            .filter { picSumEntity ->
                                favIdList.contains(picSumEntity.id)
                            }.sortedBy { picSumEntity ->
                                favIdList.indexOf(picSumEntity.id)
                            }

                    Timber.d("filteredPicSumList: $filteredPicSumList")

                    val pagingData =
                        PagingData.from(
                            filteredPicSumList.map { picSumEntity ->
                                PicSumItemFavModel(
                                    id = picSumEntity.id,
                                    author = picSumEntity.author,
                                    width = picSumEntity.width,
                                    height = picSumEntity.height,
                                    url = picSumEntity.url,
                                    downloadUrl = picSumEntity.downloadUrl,
                                    favorite = true,
                                )
                            },
                        )

                    send(pagingData)
                }
            }
    }
