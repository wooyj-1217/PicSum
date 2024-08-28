package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.domain.model.Favorite
import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateFavoriteUseCase
    @Inject
    constructor(
        private val repository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(entity: Favorite) = repository.updateFavorite(entity)
    }
