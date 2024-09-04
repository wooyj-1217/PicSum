package com.wooyj.picsum.domain.usecase.local.picsum

import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalPicSumListUseCase
    @Inject
    constructor(
        private val repo: LocalPicSumRepository,
    ) {
        suspend operator fun invoke() = repo.getPicSumList()
    }
