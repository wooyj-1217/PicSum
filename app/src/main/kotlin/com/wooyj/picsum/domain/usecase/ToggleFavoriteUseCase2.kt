package com.wooyj.picsum.domain.usecase

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class ToggleFavoriteUseCase2
    @Inject
    constructor(
        private val getPicSumItemUseCase: GetPicSumItemUseCase,
        private val isFavoriteItemUseCase: IsFavoriteItemUseCase,
    ) {
        operator fun invoke(id: String): Flow<Unit> =
            flow {
                val isFavorite = isFavoriteItemUseCase(id)
                if (isFavorite) {
                    val entity = getPicSumItemUseCase(id)
                    addFavoriteUseCase()
                } else {
                    removeFavoriteUseCase()
                }

                emit(Unit)
            }
    }
