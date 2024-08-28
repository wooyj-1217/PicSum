package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetNextIdUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalSavePicSumItemUseCase
import com.wooyj.picsum.domain.usecase.remote.RemoteGetPicSumItemUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetNextIdUseCase
    @Inject
    constructor(
        private val localGetNextIdUseCase: LocalGetNextIdUseCase,
        private val remoteGetPicSumItemUseCase: RemoteGetPicSumItemUseCase,
        private val localSavePicSumItemUseCase: LocalSavePicSumItemUseCase,
    ) {
        suspend operator fun invoke(currentId: String): String? {
            val localNextId = localGetNextIdUseCase(currentId)
            if (localNextId != null) {
                return localNextId
            } else {
                // remote에서 가져옴. -1씩 체크하면서 null이 아니면 저장하고 return. 3번정도 반복
                var currentRemoteId = currentId.toInt() + 1
                repeat(3) {
                    val nextItem = remoteGetPicSumItemUseCase(currentRemoteId.toString())
                    if (nextItem != null) {
                        // 가져온 데이터를 로컬에 저장
                        localSavePicSumItemUseCase(nextItem)
                        // 저장된 엔티티의 ID를 갱신하여 리턴
                        return nextItem.id
                    } else {
                        currentRemoteId++
                    }
                }
                return null
            }
        }
    }
