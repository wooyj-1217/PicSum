package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import com.wooyj.picsum.model.Favorite
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
