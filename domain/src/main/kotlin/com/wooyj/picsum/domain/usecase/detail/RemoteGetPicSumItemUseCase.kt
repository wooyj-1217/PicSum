package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.model.PicSum
import kotlinx.coroutines.flow.Flow

/**
 *  Remote에서 Item을 가져오는 UseCase
 */
fun interface RemoteGetPicSumItemUseCase {
    operator fun invoke(id: String): Flow<PicSum?>
}
