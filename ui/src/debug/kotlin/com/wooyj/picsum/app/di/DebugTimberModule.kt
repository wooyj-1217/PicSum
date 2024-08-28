package com.wooyj.picsum.app.di

import android.content.Context
import com.wooyj.picsum.ui.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DebugTimberModule {
    // Debug
    @Provides
    @Singleton
    fun provideTimberTreeDebug(
        @ApplicationContext context: Context,
    ): Timber.Tree =
        object : Timber.DebugTree() {
            private val tag = context.getString(R.string.app_name)

            override fun createStackElementTag(element: StackTraceElement): String =
                "(${element.fileName}:${element.lineNumber})#${element.methodName}"

            override fun log(
                priority: Int,
                tag: String?,
                message: String,
                t: Throwable?,
            ) {
                super.log(priority, tag, "$tag - $message", t)
            }
        }
}
