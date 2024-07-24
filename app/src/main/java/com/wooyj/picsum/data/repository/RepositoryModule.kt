package com.wooyj.picsum.data.repository

import com.wooyj.picsum.domain.repository.FavoriteRepository
import com.wooyj.picsum.domain.repository.PicSumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPicSumRepository(impl: PicSumRepositoryImpl): PicSumRepository

    @Binds
    abstract fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository
}
