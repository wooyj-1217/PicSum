package com.wooyj.picsum.feature.favdetail.domain

import com.wooyj.picsum.domain.usecase.favorite.RemoveFavoriteNotVisibleUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RemoveFavoriteNotVisibleUseCase
    @Inject
    constructor(
        private val usecase: RemoveFavoriteNotVisibleUseCase,
    ) {
        operator fun invoke() = usecase()
    }
