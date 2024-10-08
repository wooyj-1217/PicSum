package com.wooyj.picsum.domain.usecase.detail

import kotlinx.coroutines.flow.Flow

fun interface GetFavNextIdUseCase {
    operator fun invoke(id: String): Flow<String?>
}
