package com.wooyj.picsum.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PicSumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.d("App Start")
    }
}
