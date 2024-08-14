package com.wooyj.picsum.domain.usecase.list

import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FavoriteListUseCase
    @Inject
    constructor(
        private val favRepository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke() = favRepository.getFavoriteList()
    }
