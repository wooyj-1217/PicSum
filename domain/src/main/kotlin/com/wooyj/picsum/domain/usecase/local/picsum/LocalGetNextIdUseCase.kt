package com.wooyj.picsum.domain.usecase.local.picsum

fun interface LocalGetNextIdUseCase {
    suspend operator fun invoke(id: String): String?
}
