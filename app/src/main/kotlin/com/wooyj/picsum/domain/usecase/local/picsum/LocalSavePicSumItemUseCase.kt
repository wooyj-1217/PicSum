package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.domain.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalSavePicSumItemUseCase
    @Inject
    constructor(
        private val repository: LocalPicSumRepository,
    ) {
        suspend operator fun invoke(entity: PicSumEntity) = repository.insert(entity)
    }
