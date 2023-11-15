package com.android.sampleTest

import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {

    private var lifecycleEventObserver = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                MyApp.applicationContext = this
            }
            Lifecycle.Event.ON_RESUME -> {
                // on resume of the application lifecycle
            }
            Lifecycle.Event.ON_PAUSE -> {
                // on pause of the application lifecycle
            }
            Lifecycle.Event.ON_DESTROY -> {
                // on destroy of the application lifecycle
            }
            else -> {}
        }
    }

    override fun onCreate() {
        super.onCreate()
        myApp = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleEventObserver)
    }

    companion object {
        private var myApp: MyApp? = null
        private lateinit var applicationContext: Application

        fun getMyApp(): MyApp? {
            return myApp
        }

        fun getApplicationContext(): Context {
            return applicationContext
        }
    }

}