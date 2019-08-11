package com.urbanist.parking.feature.report.presentation.dagger

import androidx.lifecycle.ViewModelProvider
import com.memebattle.memes.mvvm.viewmodel.ViewModelFactory
import com.urbanist.parking.feature.report.domain.usecase.transform.GetBase64StringFromBitmapUseCase
import com.urbanist.parking.feature.report.domain.usecase.report.SendReportUseCase
import com.urbanist.parking.feature.report.presentation.ReportActivity
import com.urbanist.parking.feature.report.presentation.ReportViewModel
import dagger.Module
import dagger.Provides

@Module
class ReportActivityModule {

	@Provides
	fun provideViewModel(
		context: ReportActivity,
		sendReportUseCase: SendReportUseCase,
		getBase64StringFromBitmapUseCase: GetBase64StringFromBitmapUseCase
	): ReportViewModel = ViewModelFactory {
		ReportViewModel(
			sendReportUseCase,
			getBase64StringFromBitmapUseCase
		)
	}.let { viewModelFactory ->
		ViewModelProvider(context, viewModelFactory)[ReportViewModel::class.java]
	}
}