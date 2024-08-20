package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

/**
 *
 *
 *
 */
@Reusable
class RemoveFavoriteUseCase
    @Inject
    constructor(
        private val repository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.removeFavorite(id)
    }
