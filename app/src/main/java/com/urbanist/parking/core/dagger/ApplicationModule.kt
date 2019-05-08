package com.urbanist.parking.core.dagger

import com.urbanist.parking.core.network.RetrofitModule
import com.urbanist.parking.feature.report.ReportActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class
    ]
)
interface ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    fun reportActivityInjector(): ReportActivity

}