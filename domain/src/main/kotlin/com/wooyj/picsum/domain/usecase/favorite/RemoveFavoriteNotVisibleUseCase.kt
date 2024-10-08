package com.wooyj.picsum.domain.usecase.favorite

import kotlinx.coroutines.flow.Flow

/**
 *
 * favorite Repository의 visible이 false인 데이터 전체 삭제
 *
 */
fun interface RemoveFavoriteNotVisibleUseCase {
    operator fun invoke(): Flow<Int>
}
