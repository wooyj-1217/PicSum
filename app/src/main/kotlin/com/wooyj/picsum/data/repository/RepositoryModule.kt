package com.wooyj.picsum.data.repository

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepositoryImpl
import com.wooyj.picsum.data.repository.local.LocalPicSumRepositoryImpl
import com.wooyj.picsum.data.repository.paging.PagingPicSumRepositoryImpl
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import com.wooyj.picsum.domain.repository.local.LocalFavoriteRepository
import com.wooyj.picsum.domain.repository.local.LocalPicSumRepository
import com.wooyj.picsum.domain.repository.paging.PagingPicSumRepository
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

    @Binds
    abstract fun bindPagingPicSumRepository(impl: PagingPicSumRepositoryImpl): PagingPicSumRepository
}
