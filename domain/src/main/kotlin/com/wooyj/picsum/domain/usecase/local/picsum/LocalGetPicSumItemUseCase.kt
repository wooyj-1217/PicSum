package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.model.PicSum
import kotlinx.coroutines.flow.Flow

fun interface LocalGetPicSumItemUseCase {
    operator fun invoke(id: String): Flow<PicSum?>
}
