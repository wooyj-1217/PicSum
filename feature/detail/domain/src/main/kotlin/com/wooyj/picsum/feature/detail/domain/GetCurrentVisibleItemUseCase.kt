package com.wooyj.picsum.feature.detail.domain

import com.wooyj.picsum.domain.usecase.favorite.GetFavoriteUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPicSumItemUseCase
import com.wooyj.picsum.model.PicSumItemFavModel
import com.wooyj.picsum.model.toPicSumItemFavModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetCurrentVisibleItemUseCase
    @Inject
    constructor(
        private val localItemUseCase: LocalGetPicSumItemUseCase,
        private val getFavoriteUseCase: GetFavoriteUseCase,
    ) {
        operator fun invoke(currentId: String): Flow<PicSumItemFavModel> = flow {
            val item = localItemUseCase(currentId).firstOrNull()
            val favItem = getFavoriteUseCase(currentId).firstOrNull()

            item?.let {
                emit(it.toPicSumItemFavModel(favItem!!.visible))
            }
        }
    }
