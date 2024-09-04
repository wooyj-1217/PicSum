package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import com.wooyj.picsum.model.Favorite
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
