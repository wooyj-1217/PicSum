package com.wooyj.picsum.domain.usecase.remote

import com.wooyj.picsum.domain.model.PicSum
import com.wooyj.picsum.domain.usecase.local.picsum.LocalSavePicSumItemUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RemoteGetItemAndSaveUseCase
    @Inject
    constructor(
        private val getRemotePicSumItemUseCase: RemoteGetPicSumItemUseCase,
        private val savePicSumItemUseCase: LocalSavePicSumItemUseCase,
    ) {
        suspend operator fun invoke(id: String): PicSum? {
            val data = getRemotePicSumItemUseCase(id)!!
            return try {
                savePicSumItemUseCase(data)
                data
            } catch (e: Exception) {
                // 실패한 경우 null 반환
                null
            }
        }
    }
