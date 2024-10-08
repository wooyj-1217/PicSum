package com.wooyj.picsum.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

/**
 *
 * FavoriteEntity 추가 (visible = true)
 *
 */
fun interface AddFavoriteUseCase {
    operator fun invoke(entity: com.wooyj.picsum.model.Favorite): Flow<Long>
}
