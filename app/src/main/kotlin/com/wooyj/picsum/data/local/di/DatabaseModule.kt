package com.wooyj.picsum.data.local.di

import android.content.Context
import androidx.room.Room
import com.wooyj.picsum.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "app_database"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providePicSumDao(database: AppDatabase) = database.picSumDao()

    @Provides
    @Singleton
    fun providePicSumDaoFlow(database: AppDatabase) = database.picSumDaoFlow()

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDatabase) = database.favoriteDao()

    @Provides
    @Singleton
    fun provideFavoriteDaoFlow(database: AppDatabase) = database.favoriteDAOFlow()

    @Provides
    @Singleton
    fun providePicSumWithFavoriteDaoFlow(database: AppDatabase) = database.picSumWithFavoriteDaoFlow()
}
