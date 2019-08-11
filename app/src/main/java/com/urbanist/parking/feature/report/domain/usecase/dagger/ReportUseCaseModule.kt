package com.urbanist.parking.feature.report.domain.usecase.dagger

import com.urbanist.parking.feature.report.domain.usecase.report.ReportRepository
import com.urbanist.parking.feature.report.domain.usecase.report.SendReportUseCase
import com.urbanist.parking.feature.report.domain.usecase.report.SendReportUseCaseImpl
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCase
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCaseImpl
import com.urbanist.parking.feature.report.domain.usecase.transform.TransformRepository
import dagger.Module
import dagger.Provides

@Module
class ReportUseCaseModule {

	@Provides
	fun provideSendReportUseCase(reportRepository: ReportRepository): SendReportUseCase =
		SendReportUseCaseImpl(reportRepository)

	@Provides
	fun provideGetBase64StringFromBitmapUseCase(transformRepository: TransformRepository): GetBase64StringFromBitmapUseCase =
		GetBase64StringFromBitmapUseCaseImpl(transformRepository)
}