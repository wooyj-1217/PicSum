package com.wooyj.picsum.feature.favorite.domain

import com.wooyj.picsum.domain.usecase.favorite.LocalPicSumFavListUseCase
import com.wooyj.picsum.model.PicSumItemFavModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class FavoriteListUseCase
    @Inject
    constructor(
        private val picSumWithFavListUseCase: LocalPicSumFavListUseCase,
    ) {
        operator fun invoke(): Flow<List<PicSumItemFavModel>> =
            picSumWithFavListUseCase().map { picSumWithFavList ->
                picSumWithFavList.map {
                    PicSumItemFavModel(
                        id = it.id,
                        author = it.author,
                        width = it.width,
                        height = it.height,
                        url = it.url,
                        downloadUrl = it.downloadUrl,
                        isFavorite = it.isFavorite,
                    )
                }
            }
    }
