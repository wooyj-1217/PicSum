package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.model.Favorite
import kotlinx.coroutines.flow.Flow

/**
 *
 * id값이 동일한 FavoriteEntity를 반환
 *
 */
fun interface GetFavoriteUseCase {
    operator fun invoke(id: String): Flow<Favorite?>
}
