package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class ToggleFavoriteUseCase
    @Inject
    constructor(
        private val isFavoriteItemUseCase: IsFavoriteItemUseCase,
        private val addFavoriteUseCase: AddFavoriteUseCase,
        private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    ) {
        operator fun invoke(id: String): Flow<String> =
            flow {
                val isFavorite = isFavoriteItemUseCase(id)
                if (!isFavorite) {
                    addFavoriteUseCase(FavoriteEntity(id = id))
                } else {
                    removeFavoriteUseCase(id)
                }
            }
    }
