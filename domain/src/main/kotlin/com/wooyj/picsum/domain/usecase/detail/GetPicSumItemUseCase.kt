package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.data.repository.remote.RemotePicSumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped

/**
 *  Remote에서 Item을 가져오는 UseCase
 */
// @Reusable
// class GetPicSumItemUseCase
//    @Inject
//    constructor(
//        private val repository: RemotePicSumRepository,
//    ) {
//        suspend operator fun invoke(id: String) = repository.getPicSumItem(id)
//    }

// Droid Knights 2023 - Bean class

fun interface GetPicSumItemUseCase {
    suspend operator fun invoke(id: String): com.wooyj.picsum.model.PicSum?
}

@Module
@InstallIn(ViewModelScoped::class)
object UseCaseModule {
    @Provides
    fun provideGetPicSumItemUseCase(repository: RemotePicSumRepository): GetPicSumItemUseCase =
        GetPicSumItemUseCase { id -> repository.getPicSumItem(id) }
}
