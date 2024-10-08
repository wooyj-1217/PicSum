package com.wooyj.picsum.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

fun interface LocalPicSumFavListUseCase {
    operator fun invoke(): Flow<List<com.wooyj.picsum.model.PicSumItemFavModel>>
}
