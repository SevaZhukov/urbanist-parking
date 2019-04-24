package com.urbanist.parking.core.dagger

import com.urbanist.parking.core.dagger.module.ContextModule
import com.urbanist.parking.core.dagger.module.FireModule
import com.urbanist.parking.core.dagger.module.GeoModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, FireModule::class, GeoModule::class])
interface AppComponent {
    companion object {
        lateinit var instance: AppComponent
    }
}