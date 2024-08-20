package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FavoriteVisibleListUseCase
    @Inject
    constructor(
        private val favRepository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke() = favRepository.getVisibleFavoriteList()
    }
