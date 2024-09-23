package com.wooyj.picsum.domain.usecase.detail

fun interface GetFavNextIdUseCase {
    suspend operator fun invoke(id: String): String?
}
