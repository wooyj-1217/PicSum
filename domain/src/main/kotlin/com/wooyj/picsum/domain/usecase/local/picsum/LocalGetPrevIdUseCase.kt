package com.wooyj.picsum.domain.usecase.local.picsum

fun interface LocalGetPrevIdUseCase {
    suspend operator fun invoke(currentId: String): String?
}
