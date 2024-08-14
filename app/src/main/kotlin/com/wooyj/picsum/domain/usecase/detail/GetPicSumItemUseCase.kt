package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import dagger.Reusable
import javax.inject.Inject

/**
 *  Remote에서 Item을 가져오는 UseCase
 */
@Reusable
class GetPicSumItemUseCase
    @Inject
    constructor(
        private val repository: RemotePicSumRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.getPicSumItem(id)
    }
