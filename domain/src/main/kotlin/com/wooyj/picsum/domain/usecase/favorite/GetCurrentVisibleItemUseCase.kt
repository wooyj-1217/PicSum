package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.domain.model.PicSumItemFavModel
import com.wooyj.picsum.domain.model.toPicSumItemFavModel
import com.wooyj.picsum.domain.usecase.local.favorite.GetFavoriteUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPicSumItemUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCurrentVisibleItemUseCase
    @Inject
    constructor(
        private val localItemUseCase: LocalGetPicSumItemUseCase,
        private val getFavoriteUseCase: GetFavoriteUseCase,
    ) {
        suspend operator fun invoke(currentId: String): PicSumItemFavModel {
            val item = localItemUseCase(currentId)!!
            val favItem = getFavoriteUseCase(currentId)

            return item.toPicSumItemFavModel(favItem!!.visible)
        }
    }
