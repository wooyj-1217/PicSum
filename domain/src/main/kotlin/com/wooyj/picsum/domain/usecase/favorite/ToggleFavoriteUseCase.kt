package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.model.Favorite
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
                    addFavoriteUseCase(
                        com.wooyj.picsum.model
                            .Favorite(id = id),
                    )
                } else {
                    removeFavoriteUseCase(id)
                }
            }
    }
