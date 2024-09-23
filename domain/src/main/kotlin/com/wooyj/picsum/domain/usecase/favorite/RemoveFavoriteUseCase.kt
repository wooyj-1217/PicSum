package com.wooyj.picsum.domain.usecase.favorite

/**
 *
 *
 *
 */
fun interface RemoveFavoriteUseCase {
    suspend operator fun invoke(id: String): Int
}
