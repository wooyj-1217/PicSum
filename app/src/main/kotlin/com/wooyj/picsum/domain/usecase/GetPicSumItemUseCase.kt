package com.wooyj.picsum.domain.usecase

import com.wooyj.picsum.domain.repository.PicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPicSumItemUseCase
    @Inject
    constructor(
        private val repository: PicSumRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.getPicSumItem(id)
    }
