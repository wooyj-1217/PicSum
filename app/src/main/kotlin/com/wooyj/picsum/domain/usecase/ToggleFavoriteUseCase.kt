package com.wooyj.picsum.domain.usecase

import com.wooyj.picsum.data.local.room.entity.FavoriteEntity
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.domain.repository.LocalFavoriteRepository
import com.wooyj.picsum.domain.repository.LocalPicSumRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ToggleFavoriteUseCase
    @Inject
    constructor(
        private val localPicSumRepository: LocalPicSumRepository,
        private val favoriteRepository: LocalFavoriteRepository,
    ) {
        suspend operator fun invoke(entity: PicSumEntity) {
            val favoriteEntity =
                FavoriteEntity(
                    id = entity.id,
                    author = entity.author,
                    downloadUrl = entity.downloadUrl,
                    height = entity.height,
                    width = entity.width,
                    url = entity.url,
                )
            localPicSumRepository.updateFavoriteById(entity)
            favoriteRepository.added(entity.id).let { added ->
                if (added) {
                    favoriteRepository.removeFavorite(favoriteEntity)
                } else {
                    favoriteRepository.addFavorite(favoriteEntity)
                }
            }
        }
    }
