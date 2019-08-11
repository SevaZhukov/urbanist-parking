package com.urbanist.parking.core.dagger.module

import com.urbanist.parking.core.dagger.scope.ActivityScope
import com.urbanist.parking.feature.report.data.repository.dagger.ReportRepositoryModule
import com.urbanist.parking.feature.report.domain.usecase.dagger.ReportUseCaseModule
import com.urbanist.parking.feature.report.presentation.ReportActivity
import com.urbanist.parking.feature.report.presentation.dagger.ReportActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ReportRepositoryModule::class, ReportUseCaseModule::class])
interface ReportModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [ReportActivityModule::class])
	fun reportActivityInjector(): ReportActivity
}