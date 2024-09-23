package com.wooyj.picsum.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

fun interface LocalPicSumFavListUseCase {
    suspend operator fun invoke(): Flow<List<com.wooyj.picsum.model.PicSumItemFavModel>>
}
