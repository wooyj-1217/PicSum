package com.wooyj.picsum.feature.setting

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SettingApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
