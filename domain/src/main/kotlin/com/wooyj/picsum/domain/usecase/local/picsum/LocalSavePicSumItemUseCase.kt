package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.model.PicSum
import kotlinx.coroutines.flow.Flow

fun interface LocalSavePicSumItemUseCase {
    operator fun invoke(entity: PicSum): Flow<Long?>
}
