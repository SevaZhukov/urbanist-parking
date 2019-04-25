package com.urbanist.parking.core

import android.app.Application
import com.urbanist.parking.core.dagger.AppComponent
import com.urbanist.parking.core.dagger.DaggerAppComponent
import com.urbanist.parking.core.dagger.module.ContextModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponent.instance = DaggerAppComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }
}