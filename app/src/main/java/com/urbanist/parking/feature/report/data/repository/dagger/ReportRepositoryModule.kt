package com.urbanist.parking.feature.report.data.repository.dagger

import com.urbanist.parking.feature.report.data.datasource.dagger.ReportDataSourceModule
import com.urbanist.parking.feature.report.data.datasource.report.ReportDataSource
import com.urbanist.parking.feature.report.data.datasource.transform.TransformDataSource
import com.urbanist.parking.feature.report.data.repository.ReportRepositoryImpl
import com.urbanist.parking.feature.report.data.repository.TransformRepositoryImpl
import com.urbanist.parking.feature.report.domain.usecase.report.ReportRepository
import com.urbanist.parking.feature.report.domain.usecase.transform.TransformRepository
import dagger.Module
import dagger.Provides

@Module(includes = [ReportDataSourceModule::class])
class ReportRepositoryModule {

	@Provides
	fun provideTransformRepository(transformDataSource: TransformDataSource): TransformRepository = TransformRepositoryImpl(transformDataSource)

	@Provides
	fun provideRepository(networkReportDataSource: ReportDataSource): ReportRepository = ReportRepositoryImpl(networkReportDataSource)
}