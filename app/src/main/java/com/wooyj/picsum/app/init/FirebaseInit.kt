package com.wooyj.picsum.app.init

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.FirebaseApp
import timber.log.Timber

class FirebaseInit : Initializer<Unit> {
    override fun create(context: Context) {
        FirebaseApp.initializeApp(context)
//        FirebaseCrashlytics.getInstance()
        Timber.d("Firebase Init")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(TimberInit::class.java)
}
