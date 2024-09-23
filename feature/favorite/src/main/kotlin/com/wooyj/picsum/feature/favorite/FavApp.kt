package com.wooyj.picsum.feature.favorite

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FavApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
