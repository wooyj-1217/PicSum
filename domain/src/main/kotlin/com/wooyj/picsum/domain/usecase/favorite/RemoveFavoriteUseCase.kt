package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

/**
 *
 *
 *
 */
//@Reusable
//class RemoveFavoriteUseCase
//    @Inject
//    constructor(
//        private val repository: LocalFavoriteRepository,
//    ) {
//        suspend operator fun invoke(id: String) = repository.removeFavorite(id)
//    }
fun interface RemoveFavoriteUseCase {
    suspend operator fun invoke(id: String): Int
}
