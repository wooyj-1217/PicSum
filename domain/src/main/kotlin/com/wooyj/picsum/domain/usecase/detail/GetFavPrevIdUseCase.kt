package com.wooyj.picsum.domain.usecase.detail

import kotlinx.coroutines.flow.Flow

fun interface GetFavPrevIdUseCase {
    operator fun invoke(id: String): Flow<String?>
}
