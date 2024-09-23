package com.wooyj.picsum.domain.usecase.local.picsum

fun interface LocalGetPicSumItemUseCase {
    suspend operator fun invoke(id: String): com.wooyj.picsum.model.PicSum?
}
