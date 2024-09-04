package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
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
