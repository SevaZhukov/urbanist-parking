package com.urbanist.parking.core.dagger

import com.urbanist.parking.core.App
import com.urbanist.parking.core.dagger.module.ApplicationModule
import com.urbanist.parking.core.dagger.scope.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<App> {

	@Component.Factory
	abstract class Factory: AndroidInjector.Factory<App>
}