package com.urbanist.parking.feature.report.data.repository

import com.urbanist.parking.feature.report.data.datasource.report.ReportDataSource
import com.urbanist.parking.feature.report.domain.usecase.report.ReportRepository
import com.urbanist.parking.feature.report.domain.entity.Report
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

class ReportRepositoryImpl(private val reportDataSource: ReportDataSource) : ReportRepository {

	// TODO выпилить управление скедулерами из репозитория
	override fun sendReport(report: Report): Completable {
		return reportDataSource.sendReport(report)
			.observeOn(AndroidSchedulers.mainThread())
	}
}