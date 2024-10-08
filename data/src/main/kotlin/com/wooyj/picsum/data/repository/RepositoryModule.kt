package com.wooyj.picsum.data.repository

import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import com.wooyj.picsum.data.repository.local.LocalFavoriteRepositoryImpl
import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import com.wooyj.picsum.data.repository.local.LocalPicSumRepositoryImpl
import com.wooyj.picsum.data.repository.local.LocalPicSumWithFavRepository
import com.wooyj.picsum.data.repository.local.LocalPicSumWithFavRepositoryImpl
import com.wooyj.picsum.data.repository.paging.PagingPicSumRepository
import com.wooyj.picsum.data.repository.paging.PagingPicSumRepositoryImpl
import com.wooyj.picsum.data.repository.remote.RemotePicSumRepository
import com.wooyj.picsum.data.repository.remote.RemotePicSumRepositoryImpl
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

    @Binds
    abstract fun bindLocalPicSumWithFavRepository(impl: LocalPicSumWithFavRepositoryImpl): LocalPicSumWithFavRepository
}
