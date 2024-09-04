package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
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
        suspend operator fun invoke(entity: com.wooyj.picsum.model.Favorite) = repository.addFavorite(entity)
    }
