package com.urbanist.parking.core.dagger.module

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