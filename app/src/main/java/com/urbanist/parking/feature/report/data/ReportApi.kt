package com.urbanist.parking.feature.report.data

import com.urbanist.parking.feature.report.domain.model.Report
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface ReportApi {
	@POST("/")
	fun sendReport(@Body report: Report): Completable
}