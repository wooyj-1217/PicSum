package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalGetPrevIdUseCase
    @Inject
    constructor(
        private val repository: LocalPicSumRepository,
    ) {
        suspend operator fun invoke(currentId: String) = repository.getPrevId(currentId)
    }
