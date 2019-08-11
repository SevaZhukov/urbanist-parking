package com.urbanist.parking.feature.report.data.datasource.report

import com.urbanist.parking.feature.report.domain.entity.Report
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface ReportApi {

	@POST("reports")
	fun sendReport(@Body report: Report): Completable
}