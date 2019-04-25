package com.urbanist.parking.core.dagger

import com.urbanist.parking.core.dagger.module.ContextModule
import com.urbanist.parking.core.dagger.module.FireModule
import com.urbanist.parking.feature.report.ReportActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, FireModule::class])
interface AppComponent {
    fun inject(reportActivity: ReportActivity)

    companion object {
        lateinit var instance: AppComponent
    }
}