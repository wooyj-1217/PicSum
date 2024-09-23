package com.wooyj.picsum.domain.usecase.favorite

fun interface IsFavoriteItemUseCase {
    suspend operator fun invoke(id: String): Boolean
}
