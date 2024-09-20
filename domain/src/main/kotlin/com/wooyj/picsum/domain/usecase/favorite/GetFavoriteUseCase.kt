package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

/**
 *
 * id값이 동일한 FavoriteEntity를 반환
 *
 */
//@Reusable
//class GetFavoriteUseCase
//    @Inject
//    constructor(
//        private val repository: LocalFavoriteRepository,
//    ) {
//        suspend operator fun invoke(id: String) = repository.getFavoriteItem(id)
//    }
fun interface GetFavoriteUseCase {
    suspend operator fun invoke(id: String): com.wooyj.picsum.model.Favorite?
}
