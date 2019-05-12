package com.urbanist.parking.feature.report.domain.repository

import com.urbanist.parking.feature.report.domain.model.Report
import io.reactivex.Completable

interface ReportRepository {
	fun sendReport(report: Report): Completable
}