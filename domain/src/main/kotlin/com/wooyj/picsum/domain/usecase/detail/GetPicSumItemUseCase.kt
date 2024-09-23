package com.wooyj.picsum.domain.usecase.detail

// Droid Knights 2023 - Bean class

/**
 *  Remote에서 Item을 가져오는 UseCase
 */
fun interface GetPicSumItemUseCase {
    suspend operator fun invoke(id: String): com.wooyj.picsum.model.PicSum?
}
