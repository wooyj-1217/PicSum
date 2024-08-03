package com.wooyj.picsum.data.repository

import com.wooyj.picsum.domain.repository.LocalFavoriteRepository
import com.wooyj.picsum.domain.repository.LocalPicSumRepository
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRemotePicSumRepository(impl: RemotePicSumRepositoryImpl): RemotePicSumRepository

    @Binds
    abstract fun bindLocalPicSumRepository(impl: LocalPicSumRepositoryImpl): LocalPicSumRepository

    @Binds
    abstract fun bindLocalFavoriteRepository(impl: LocalFavoriteRepositoryImpl): LocalFavoriteRepository
}
