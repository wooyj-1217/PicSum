package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.domain.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalGetNextIdUseCase
    @Inject
    constructor(
        private val repository: LocalPicSumRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.getNextId(currentId = id)
    }
