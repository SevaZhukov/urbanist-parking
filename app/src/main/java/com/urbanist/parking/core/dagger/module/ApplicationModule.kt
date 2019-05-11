package com.urbanist.parking.core.dagger.module

import com.urbanist.parking.core.dagger.scope.ActivityScope
import com.urbanist.parking.core.network.RetrofitModule
import com.urbanist.parking.feature.report.ReportActivity
import com.urbanist.parking.feature.report.di.ReportNetworkModule
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
    @ContributesAndroidInjector(modules = [ReportNetworkModule::class])
    fun reportActivityInjector(): ReportActivity
}