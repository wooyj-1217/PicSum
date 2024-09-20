package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import dagger.Reusable
import javax.inject.Inject

/**
 *
 * favorite Repository의 visible이 false인 데이터 전체 삭제
 *
 */
//@Reusable
//class RemoveFavoriteNotVisibleUseCase
//    @Inject
//    constructor(
//        private val favoriteRepository: LocalFavoriteRepository,
//    ) {
//        suspend operator fun invoke() = favoriteRepository.removeFavoriteNotVisible()
//    }
fun interface RemoveFavoriteNotVisibleUseCase {
    suspend operator fun invoke(): Int
}
