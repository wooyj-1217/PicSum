package com.wooyj.picsum.domain.usecase.favorite

fun interface UpdateFavoriteUseCase {
    suspend operator fun invoke(entity: com.wooyj.picsum.model.Favorite): Int
}
