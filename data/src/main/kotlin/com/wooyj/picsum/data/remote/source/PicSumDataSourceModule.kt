package com.wooyj.picsum.data.remote.source

import com.wooyj.picsum.data.source.PicSumRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PicSumDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindPicSumRemoteDataSource(impl: PicSumRemoteDataSourceImpl): PicSumRemoteDataSource
}
