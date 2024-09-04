package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalGetPicSumItemUseCase
    @Inject
    constructor(
        private val localPicSumRepository: LocalPicSumRepository,
    ) {
        suspend operator fun invoke(id: String) = localPicSumRepository.getPicSumItem(id)
    }
