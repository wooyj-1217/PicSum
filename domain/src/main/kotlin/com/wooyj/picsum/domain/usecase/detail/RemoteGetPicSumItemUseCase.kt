package com.wooyj.picsum.domain.usecase.detail

/**
 *  Remote에서 Item을 가져오는 UseCase
 */
fun interface RemoteGetPicSumItemUseCase {
    suspend operator fun invoke(id: String): com.wooyj.picsum.model.PicSum?
}
