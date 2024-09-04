package com.wooyj.picsum.domain.usecase.remote

import com.wooyj.picsum.data.repository.remote.RemotePicSumRepository
import dagger.Reusable
import javax.inject.Inject

/**
 *  Remote에서 Item을 가져오는 UseCase
 */
@Reusable
class RemoteGetPicSumItemUseCase
    @Inject
    constructor(
        private val repository: RemotePicSumRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.getPicSumItem(id)
    }
