package com.wooyj.picsum.domain.usecase.favorite

/**
 *
 * id값이 동일한 FavoriteEntity를 반환
 *
 */
fun interface GetFavoriteUseCase {
    suspend operator fun invoke(id: String): com.wooyj.picsum.model.Favorite?
}
