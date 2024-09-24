package com.wooyj.picsum.feature.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.d(":feature:main:MainApp")
    }
}
