package com.wooyj.picsum.domain.usecase.local.picsum

import kotlinx.coroutines.flow.Flow

fun interface LocalGetNextIdUseCase {
    operator fun invoke(id: String): Flow<String?>
}
