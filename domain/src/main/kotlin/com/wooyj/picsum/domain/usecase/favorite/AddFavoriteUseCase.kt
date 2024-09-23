package com.wooyj.picsum.domain.usecase.favorite

/**
 *
 * FavoriteEntity 추가 (visible = true)
 *
 */
fun interface AddFavoriteUseCase {
    suspend operator fun invoke(entity: com.wooyj.picsum.model.Favorite): Long
}
