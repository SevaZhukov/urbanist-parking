package com.urbanist.parking.core.dagger

import android.content.Context
import com.urbanist.parking.core.App
import com.urbanist.parking.core.dagger.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<App> {

	@Component.Builder
	abstract class Builder : AndroidInjector.Builder<App>() {

		@BindsInstance
		abstract fun context(context: Context): Builder
	}
}