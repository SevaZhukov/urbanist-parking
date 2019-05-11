package com.urbanist.parking.feature.report.di

import com.urbanist.parking.core.dagger.scope.ActivityScope
import com.urbanist.parking.feature.report.data.ReportApi
import com.urbanist.parking.feature.report.domain.interactor.ReportService
import com.urbanist.parking.feature.report.domain.interactor.impl.ReportServiceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ReportNetworkModule {
	@Provides
	@ActivityScope
	fun provideApi(retrofit: Retrofit): ReportApi = retrofit.create(ReportApi::class.java)

	@Provides
	@ActivityScope
	fun provideInteractor(reportApi: ReportApi): ReportService = ReportServiceImpl(reportApi)
}