package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateFavoriteUseCase
    @Inject
    constructor(
        private val repository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(entity: com.wooyj.picsum.model.Favorite) = repository.updateFavorite(entity)
    }
