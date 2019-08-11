package com.urbanist.parking.feature.report.domain.usecase.report

import com.urbanist.parking.feature.report.domain.entity.Report
import io.reactivex.Completable

interface ReportRepository {

	fun sendReport(report: Report): Completable
}