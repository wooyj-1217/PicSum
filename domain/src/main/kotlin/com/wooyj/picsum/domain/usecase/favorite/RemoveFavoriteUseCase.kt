package com.wooyj.picsum.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

/**
 *
 *
 *
 */
fun interface RemoveFavoriteUseCase {
    operator fun invoke(id: String): Flow<Int>
}
