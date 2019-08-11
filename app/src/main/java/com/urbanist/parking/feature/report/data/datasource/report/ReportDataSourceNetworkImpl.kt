package com.urbanist.parking.feature.report.data.datasource.report

import com.urbanist.parking.feature.report.domain.entity.Report
import io.reactivex.Completable

class ReportDataSourceNetworkImpl(private val reportApi: ReportApi) : ReportDataSource {

	override fun sendReport(report: Report): Completable = reportApi.sendReport(report)
}