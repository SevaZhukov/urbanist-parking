package com.urbanist.parking.feature.report.data.datasource.report

import com.urbanist.parking.feature.report.domain.entity.Report
import io.reactivex.Completable

interface ReportDataSource {

	fun sendReport(report: Report): Completable
}