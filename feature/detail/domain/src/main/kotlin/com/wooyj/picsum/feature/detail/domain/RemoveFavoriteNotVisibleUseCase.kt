package com.wooyj.picsum.feature.detail.domain

import com.wooyj.picsum.domain.usecase.favorite.RemoveFavoriteNotVisibleUseCase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RemoveFavoriteNotVisibleUseCase
    @Inject
    constructor(
        private val usecase: RemoveFavoriteNotVisibleUseCase,
    ) {
        operator fun invoke() = usecase()
    }

// TODO : 빈혈클래스인 UseCase인 경우는 다음과 같이 Flow로 변환해서 바꾼다!!
