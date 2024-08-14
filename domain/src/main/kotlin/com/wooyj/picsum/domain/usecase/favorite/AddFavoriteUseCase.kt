package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

/**
 *
 * FavoriteEntity 추가 (visible = true)
 *
 */
@Reusable
class AddFavoriteUseCase
    @Inject
    constructor(
        private val repository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(entity: FavoriteEntity) = repository.addFavorite(entity)
    }
