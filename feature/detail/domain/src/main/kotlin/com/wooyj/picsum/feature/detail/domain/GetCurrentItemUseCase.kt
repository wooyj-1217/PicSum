package com.wooyj.picsum.feature.detail.domain

import com.wooyj.picsum.domain.usecase.favorite.GetFavoriteUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPicSumItemUseCase
import com.wooyj.picsum.model.PicSumItemFavModel
import com.wooyj.picsum.model.toPicSumItemFavModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetCurrentItemUseCase
    @Inject
    constructor(
        private val localItemUseCase: LocalGetPicSumItemUseCase,
        private val remoteGetItemAndSaveUseCase: RemoteGetItemAndSaveUseCase,
        private val getFavoriteUseCase: GetFavoriteUseCase,
    ) {
        operator fun invoke(currentId: String): Flow<PicSumItemFavModel> = flow {
            val favItem = getFavoriteUseCase(currentId).firstOrNull()
            val localModel = localItemUseCase(currentId).firstOrNull()
            val isFavorite =
                favItem?.visible ?: false
            if (localModel != null) {
                emit(localModel.toPicSumItemFavModel(isFavorite))
            } else {
                try {
                    val entity = remoteGetItemAndSaveUseCase(currentId).firstOrNull()
                    entity?.let {
                        val model = entity.toPicSumItemFavModel(isFavorite)
                        emit(model)
                    }
                } catch (e: Exception) {
                    // 실패 처리 (예외를 던짐)
                    throw Exception("Failed to fetch or save item with id $currentId", e)
                }
            }
        }
    }
