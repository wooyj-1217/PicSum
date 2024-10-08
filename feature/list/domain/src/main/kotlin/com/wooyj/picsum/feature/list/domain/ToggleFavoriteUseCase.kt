package com.wooyj.picsum.feature.list.domain

import com.wooyj.picsum.domain.usecase.favorite.AddFavoriteUseCase
import com.wooyj.picsum.domain.usecase.favorite.IsFavoriteItemUseCase
import com.wooyj.picsum.domain.usecase.favorite.RemoveFavoriteUseCase
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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
                val isFavorite = isFavoriteItemUseCase(id).first()
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
