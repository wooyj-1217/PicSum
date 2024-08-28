package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.domain.model.PicSum
import com.wooyj.picsum.domain.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalSavePicSumItemUseCase
    @Inject
    constructor(
        private val repository: LocalPicSumRepository,
    ) {
        suspend operator fun invoke(entity: PicSum) = repository.insert(entity)
    }
