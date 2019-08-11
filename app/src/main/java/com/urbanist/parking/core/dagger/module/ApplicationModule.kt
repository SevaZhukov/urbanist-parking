package com.urbanist.parking.core.dagger.module

import com.urbanist.parking.core.network.RetrofitModule
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class,
        ReportModule::class
    ]
)
interface ApplicationModule