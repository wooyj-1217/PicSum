package com.wooyj.picsum.domain.usecase.detail

fun interface GetFavPrevIdUseCase {
    suspend operator fun invoke(id: String): String?
}
