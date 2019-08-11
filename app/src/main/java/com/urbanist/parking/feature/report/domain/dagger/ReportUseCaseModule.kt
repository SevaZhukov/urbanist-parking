package com.urbanist.parking.feature.report.domain.dagger

import com.urbanist.parking.core.dagger.scope.ActivityScope
import com.urbanist.parking.feature.report.domain.usecase.report.ReportRepository
import com.urbanist.parking.feature.report.domain.usecase.report.SendReportUseCase
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCase
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCaseImpl
import com.urbanist.parking.feature.report.domain.usecase.transform.TransformRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ReportUseCaseModule.BindsModule::class])
class ReportUseCaseModule {

	@Module
	interface BindsModule {

		@Binds
		fun provideSendReportUseCase(reportRepository: ReportRepository): SendReportUseCase

		@Provides
		@ActivityScope
		fun provideGetBase64StringFromBitmapUseCase(transformRepository: TransformRepository): GetBase64StringFromBitmapUseCase =
			GetBase64StringFromBitmapUseCaseImpl(transformRepository)
	}
}