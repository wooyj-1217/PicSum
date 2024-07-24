package com.wooyj.picsum.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wooyj.picsum.data.remote.call.ResultCallAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
        moshi: Moshi,
        @BaseUrl baseUrl: String,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .addCallAdapterFactory(ResultCallAdapter.Factory())
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}
