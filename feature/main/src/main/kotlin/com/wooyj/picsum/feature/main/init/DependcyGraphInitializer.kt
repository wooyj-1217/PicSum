package com.wooyj.picsum.app.init

import android.content.Context
import androidx.startup.Initializer
import com.wooyj.picsum.feature.main.init.di.InitializerEntryPoint

class DependcyGraphInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        InitializerEntryPoint.resolve(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
