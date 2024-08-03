package com.wooyj.picsum.domain.usecase

import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPicSumItemUseCase
    @Inject
    constructor(
        private val repository: RemotePicSumRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.getPicSumItem(id)
    }
