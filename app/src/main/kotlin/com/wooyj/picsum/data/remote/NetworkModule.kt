package com.wooyj.picsum.data.remote

import com.wooyj.picsum.data.remote.call.ResultCallAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkSetting: NetworkSetting,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(networkSetting.connectionTimeOut, TimeUnit.SECONDS)
            .writeTimeout(networkSetting.writeTimeOut, TimeUnit.SECONDS)
            .readTimeout(networkSetting.readTimeOut, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl) // 삭제 예정
            .client(okHttpClient)
            .addCallAdapterFactory(ResultCallAdapter.Factory())
            .build()
}
