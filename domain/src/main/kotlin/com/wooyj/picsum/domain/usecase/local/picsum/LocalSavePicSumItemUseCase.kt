package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalSavePicSumItemUseCase
    @Inject
    constructor(
        private val repository: LocalPicSumRepository,
    ) {
        suspend operator fun invoke(entity: com.wooyj.picsum.model.PicSum) = repository.insert(entity)
    }
