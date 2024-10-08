package com.wooyj.picsum.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

fun interface UpdateFavoriteUseCase {
    operator fun invoke(entity: com.wooyj.picsum.model.Favorite): Flow<Int>
}
