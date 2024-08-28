package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPrevIdUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalSavePicSumItemUseCase
import com.wooyj.picsum.domain.usecase.remote.RemoteGetPicSumItemUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetPrevIdUseCase
    @Inject
    constructor(
        private val localGetPrevIdUseCase: LocalGetPrevIdUseCase,
        private val remoteGetPicSumItemUseCase: RemoteGetPicSumItemUseCase,
        private val localSavePicSumItemUseCase: LocalSavePicSumItemUseCase,
    ) {
        suspend operator fun invoke(currentId: String): String? {
            val localPrevId = localGetPrevIdUseCase(currentId)
            if (localPrevId != null) {
                return localPrevId
            } else {
                // remote에서 가져옴. -1씩 체크하면서 null이 아니면 저장하고 return. 3번정도 반복
                var currentRemoteId = currentId.toInt() - 1
                repeat(3) {
                    val prevItem = remoteGetPicSumItemUseCase(currentRemoteId.toString())
                    if (prevItem != null) {
                        // 가져온 데이터를 로컬에 저장
                        localSavePicSumItemUseCase(prevItem)
                        // 저장된 엔티티의 ID를 갱신하여 리턴
                        return prevItem.id
                    } else {
                        currentRemoteId--
                    }
                }
                return null
            }
        }
    }
