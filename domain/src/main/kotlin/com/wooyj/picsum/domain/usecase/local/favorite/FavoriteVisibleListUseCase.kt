package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FavoriteVisibleListUseCase
    @Inject
    constructor(
        private val favRepository: LocalFavoriteRepository,
    ) {
        operator fun invoke() = favRepository.getVisibleFavoriteList()
    }
