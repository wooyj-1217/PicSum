package com.wooyj.picsum.data.remote.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PicSumAPIService = retrofit.create(PicSumAPIService::class.java)
}
