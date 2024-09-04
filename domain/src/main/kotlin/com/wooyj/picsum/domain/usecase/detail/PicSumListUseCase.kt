package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class PicSumListUseCase
    @Inject
    constructor(
        private val repo: LocalPicSumRepository,
    ) {
        suspend operator fun invoke() = repo.getPicSumList()
    }
