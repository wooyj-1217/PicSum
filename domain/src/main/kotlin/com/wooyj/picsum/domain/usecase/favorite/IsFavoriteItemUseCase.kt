package com.wooyj.picsum.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

fun interface IsFavoriteItemUseCase {
    operator fun invoke(id: String): Flow<Boolean>
}
