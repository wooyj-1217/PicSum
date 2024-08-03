package com.wooyj.picsum.domain.usecase

import com.wooyj.picsum.domain.repository.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class IsFavoriteItemUseCase
    @Inject
    constructor(
        private val favoriteRepository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(id: String): Boolean = favoriteRepository.added(id)
    }
