package com.wooyj.picsum.feature.detail.domain

import com.wooyj.picsum.domain.usecase.favorite.AddFavoriteUseCase
import com.wooyj.picsum.domain.usecase.favorite.GetFavoriteUseCase
import com.wooyj.picsum.domain.usecase.favorite.UpdateFavoriteUseCase
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class UpdateVisibleStateUseCase
    @Inject
    constructor(
        private val getFavoriteUseCase: GetFavoriteUseCase,
        private val addFavoriteUseCase: AddFavoriteUseCase,
        private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    ) {
        operator fun invoke(id: String): Flow<String> =
            flow {
                val item = getFavoriteUseCase(id)
                if (item == null) {
                    // 없을 경우
                    addFavoriteUseCase(
                        com.wooyj.picsum.model
                            .Favorite(id = id),
                    )
                } else {
                    // 있을 경우
                    updateFavoriteUseCase(item.copy(visible = !item.visible))
                }
            }
    }
