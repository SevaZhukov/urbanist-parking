package com.urbanist.parking.feature.report.data.datasource.dagger

import com.urbanist.parking.feature.report.data.datasource.report.ReportApi
import com.urbanist.parking.feature.report.data.datasource.report.ReportDataSource
import com.urbanist.parking.feature.report.data.datasource.report.ReportDataSourceNetworkImpl
import com.urbanist.parking.feature.report.data.datasource.transform.TransformDataSource
import com.urbanist.parking.feature.report.data.datasource.transform.TransformDataSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ReportDataSourceModule {

	@Provides
	fun provideReportApi(retrofit: Retrofit): ReportApi = retrofit.create(ReportApi::class.java)

	@Provides
	fun provideTransformDataSource(): TransformDataSource = TransformDataSourceImpl()

	@Provides
	fun provideReportDataSource(reportApi: ReportApi): ReportDataSource = ReportDataSourceNetworkImpl(reportApi)
}