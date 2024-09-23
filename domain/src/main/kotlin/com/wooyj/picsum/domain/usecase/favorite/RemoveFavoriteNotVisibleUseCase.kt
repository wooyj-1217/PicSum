package com.wooyj.picsum.domain.usecase.favorite

/**
 *
 * favorite Repository의 visible이 false인 데이터 전체 삭제
 *
 */
fun interface RemoveFavoriteNotVisibleUseCase {
    suspend operator fun invoke(): Int
}
