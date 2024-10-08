package com.wooyj.picsum.feature.favdetail.domain

import com.wooyj.picsum.domain.usecase.detail.RemoteGetPicSumItemUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalSavePicSumItemUseCase
import com.wooyj.picsum.model.PicSum
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@Reusable
class RemoteGetItemAndSaveUseCase
    @Inject
    constructor(
        private val getRemotePicSumItemUseCase: RemoteGetPicSumItemUseCase,
        private val savePicSumItemUseCase: LocalSavePicSumItemUseCase,
    ) {
        operator fun invoke(id: String): Flow<PicSum?> = flow {
            getRemotePicSumItemUseCase(id)
                .onEach { picSum ->
                    picSum?.let {
                        savePicSumItemUseCase(it)
                        emit(it)
                    }
                }.catch {
                    emit(null)
                }.collect()
        }
    }
