package com.wooyj.picsum.domain.usecase.local.favorite

import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
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
                    addFavoriteUseCase(FavoriteEntity(id = id))
                } else {
                    // 있을 경우
                    updateFavoriteUseCase(item.copy(visible = !item.visible))
                }
            }
    }
