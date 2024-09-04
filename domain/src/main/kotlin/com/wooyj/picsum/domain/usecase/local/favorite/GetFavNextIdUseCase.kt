package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetFavNextIdUseCase
    @Inject
    constructor(
        private val repository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(id: String) = repository.getNextId(id)
    }
