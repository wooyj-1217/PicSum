package com.wooyj.picsum.domain.usecase.local.picsum

import kotlinx.coroutines.flow.Flow

fun interface LocalGetPrevIdUseCase {
    operator fun invoke(currentId: String): Flow<String?>
}
