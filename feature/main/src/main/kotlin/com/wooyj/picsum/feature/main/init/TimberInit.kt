package com.wooyj.picsum.app.init

import android.content.Context
import androidx.startup.Initializer
import com.wooyj.picsum.feature.main.init.di.InitializerEntryPoint
import timber.log.Timber
import javax.inject.Inject

class TimberInit : Initializer<Unit> {
    @Inject
    lateinit var tree: Timber.Tree

    override fun create(context: Context) {
        val entryPoint = InitializerEntryPoint.resolve(context)
        entryPoint.inject(this)

        // Plant the tree
        Timber.plant(tree)
        Timber.d("Timber Init")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(DependcyGraphInitializer::class.java)
}
