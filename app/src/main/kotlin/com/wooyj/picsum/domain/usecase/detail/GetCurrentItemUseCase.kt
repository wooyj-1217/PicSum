package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.domain.model.PicSumItemFavModel
import com.wooyj.picsum.domain.model.toPicSumItemFavModel
import com.wooyj.picsum.domain.usecase.local.favorite.IsFavoriteItemUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPicSumItemUseCase
import com.wooyj.picsum.domain.usecase.remote.RemoteGetItemAndSaveUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCurrentItemUseCase
    @Inject
    constructor(
        private val localItemUseCase: LocalGetPicSumItemUseCase,
        private val remoteGetItemAndSaveUseCase: RemoteGetItemAndSaveUseCase,
        private val isFavoriteItemUseCase: IsFavoriteItemUseCase,
    ) {
        suspend operator fun invoke(currentId: String): PicSumItemFavModel {
            val isFavorite = isFavoriteItemUseCase(currentId)
            val localModel = localItemUseCase(currentId)
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
