package com.wooyj.picsum.domain.usecase.local.picsum

fun interface LocalSavePicSumItemUseCase {
    suspend operator fun invoke(entity: com.wooyj.picsum.model.PicSum): Long?
}
