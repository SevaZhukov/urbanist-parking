package com.urbanist.parking.core.dagger

import android.content.Context
import com.urbanist.parking.core.App
import com.urbanist.parking.core.dagger.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<App> {

	@Component.Builder
	abstract class Builder : AndroidInjector.Builder<App>() {

		@BindsInstance
		abstract fun context(context: Context): Builder
	}
}