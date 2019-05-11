package com.urbanist.parking.feature.report.domain.interactor.impl

import com.urbanist.parking.feature.report.data.ReportApi
import com.urbanist.parking.feature.report.domain.interactor.ReportService
import com.urbanist.parking.feature.report.domain.model.Report
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

class ReportServiceImpl(private val reportApi: ReportApi) : ReportService {
	override fun sendReport(report: Report): Completable {
		return reportApi.sendReport(report)
			.observeOn(AndroidSchedulers.mainThread())
	}
}