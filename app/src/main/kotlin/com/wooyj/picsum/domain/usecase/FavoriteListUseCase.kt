package com.wooyj.picsum.domain.usecase

import com.wooyj.picsum.domain.repository.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FavoriteListUseCase
    @Inject
    constructor(
        private val favoriteRepository: LocalPicSumRepository,
    ) {
        operator fun invoke() = favoriteRepository.getFavoriteList()
    }
