package com.urbanist.parking.feature.report.domain.interactor

import com.urbanist.parking.feature.report.domain.model.Report
import io.reactivex.Completable

interface ReportService {
	fun sendReport(report: Report): Completable
}