package com.urbanist.parking.core.dagger.module

import com.urbanist.parking.core.dagger.scope.ActivityScope
import com.urbanist.parking.core.network.RetrofitModule
import com.urbanist.parking.feature.main.MainActivity
import com.urbanist.parking.feature.onboarding.OnBoardingActivity
import com.urbanist.parking.feature.recomendation.RecommendationActivity
import com.urbanist.parking.feature.report.ReportActivity
import com.urbanist.parking.feature.rules.RulesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class
    ]
)
interface ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun reportActivityInjector(): ReportActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun rulesActivityInjector(): RulesActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun recomedationActivityInjector(): RecommendationActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun onBoardingActivityInjector(): OnBoardingActivity
}