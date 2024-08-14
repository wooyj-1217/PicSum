package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateFavoriteUseCase
    @Inject
    constructor(
        private val repository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(entity: FavoriteEntity) = repository.updateFavorite(entity)
    }
