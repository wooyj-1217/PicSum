package com.wooyj.picsum.feature.detail.domain

import com.wooyj.picsum.domain.usecase.favorite.GetFavoriteUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPicSumItemUseCase
import com.wooyj.picsum.model.PicSumItemFavModel
import com.wooyj.picsum.model.toPicSumItemFavModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCurrentItemUseCase
    @Inject
    constructor(
        private val localItemUseCase: LocalGetPicSumItemUseCase,
        private val remoteGetItemAndSaveUseCase: RemoteGetItemAndSaveUseCase,
        private val getFavoriteUseCase: GetFavoriteUseCase,
    ) {
        suspend operator fun invoke(currentId: String): PicSumItemFavModel {
            val favItem = getFavoriteUseCase(currentId)
            val localModel = localItemUseCase(currentId)
            val isFavorite =
                favItem?.visible ?: false
            if (localModel != null) {
                return localModel.toPicSumItemFavModel(isFavorite)
            } else {
                try {
                    val entity = remoteGetItemAndSaveUseCase(currentId)
                    val model = entity!!.toPicSumItemFavModel(isFavorite)
                    return model
                } catch (e: Exception) {
                    // 실패 처리 (예외를 던짐)
                    throw Exception("Failed to fetch or save item with id $currentId", e)
                }
            }
        }
    }
