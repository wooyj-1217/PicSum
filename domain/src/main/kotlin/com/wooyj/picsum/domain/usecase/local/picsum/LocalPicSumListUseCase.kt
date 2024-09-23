package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.model.PicSum

fun interface LocalPicSumListUseCase {
    suspend operator fun invoke(): List<PicSum>
}
