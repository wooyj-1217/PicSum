package com.wooyj.picsum.domain.usecase.local.picsumfav

import com.wooyj.picsum.data.repository.local.LocalPicSumWithFavRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocalPicSumFavListUseCase
    @Inject
    constructor(
        private val repository: LocalPicSumWithFavRepository,
    ) {
        suspend operator fun invoke() = repository.getPicSumWithFavoriteList()
    }
