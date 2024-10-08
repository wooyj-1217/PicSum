package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.model.PicSum
import kotlinx.coroutines.flow.Flow

fun interface LocalPicSumListUseCase {
    operator fun invoke(): Flow<List<PicSum>>
}
