package com.urbanist.parking.feature.report.data.repository.dagger

import com.urbanist.parking.feature.report.data.datasource.dagger.ReportDataSourceModule
import dagger.Binds
import dagger.Module

@Module(includes = [ReportDataSourceModule::class])
interface ReportRepositoryModule {

}