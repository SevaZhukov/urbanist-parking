package com.urbanist.parking.feature.report.di

import com.urbanist.parking.core.dagger.scope.ActivityScope
import com.urbanist.parking.feature.report.data.datasource.report.ReportApi
import com.urbanist.parking.feature.report.data.datasource.report.ReportDataSourceNetworkImpl
import com.urbanist.parking.feature.report.data.datasource.report.ReportDataSource
import com.urbanist.parking.feature.report.data.datasource.transform.TransformDataSource
import com.urbanist.parking.feature.report.data.datasource.transform.TransformDataSourceImpl
import com.urbanist.parking.feature.report.domain.usecase.report.ReportRepository
import com.urbanist.parking.feature.report.data.repository.ReportRepositoryImpl
import com.urbanist.parking.feature.report.data.repository.TransformRepositoryImpl
import com.urbanist.parking.feature.report.domain.usecase.transform.TransformRepository
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCase
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCaseImpl
import com.urbanist.parking.feature.report.domain.usecase.report.SendReportUseCase
import com.urbanist.parking.feature.report.domain.usecase.report.SendReportUseCaseImpl
import com.urbanist.parking.feature.report.presentation.dagger.ReportActivityModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

// TODO разбить на модули
@Module(
	includes = [
		ReportActivityModule::class
	]
)
class ReportModule() {

	@Provides
	@ActivityScope
	fun provideReportApi(retrofit: Retrofit): ReportApi = retrofit.create(ReportApi::class.java)

	@Provides
	@ActivityScope
	fun provideReportDataSource(reportApi: ReportApi): ReportDataSource = ReportDataSourceNetworkImpl(reportApi)

	@Provides
	@ActivityScope
	fun provideRepository(networkReportDataSource: ReportDataSource): ReportRepository = ReportRepositoryImpl(networkReportDataSource)

	@Provides
	@ActivityScope
	fun provideSendReportUseCase(reportRepository: ReportRepository): SendReportUseCase =
		SendReportUseCaseImpl(reportRepository)

	@Provides
	@ActivityScope
	fun provideTransformDataSource(): TransformDataSource = TransformDataSourceImpl()

	@Provides
	@ActivityScope
	fun provideTransformRepository(transformDataSource: TransformDataSource): TransformRepository = TransformRepositoryImpl(transformDataSource)

	@Provides
	@ActivityScope
	fun provideGetBase64StringFromBitmapUseCase(transformRepository: TransformRepository): GetBase64StringFromBitmapUseCase =
		GetBase64StringFromBitmapUseCaseImpl(transformRepository)
}