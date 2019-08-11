package com.urbanist.parking.feature.report.data.datasource.dagger

import com.urbanist.parking.feature.report.data.datasource.report.ReportApi
import com.urbanist.parking.feature.report.data.datasource.report.ReportDataSource
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCase
import com.urbanist.parking.feature.report.domain.usecase.transform.TransformRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ReportDataSourceModule.BindsModule::class])
class ReportDataSourceModule {

	@Module
	interface BindsModule {

		@Binds
		fun provideReportDataSource(reportApi: ReportApi): ReportDataSource

		@Binds
		fun provideGetBase64StringFromBitmapUseCase(transformRepository: TransformRepository): GetBase64StringFromBitmapUseCase
	}
}