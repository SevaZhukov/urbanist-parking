package com.urbanist.parking.core.dagger

import android.content.Context
import com.urbanist.parking.core.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
@ApplicationScope
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {

        @BindsInstance
        abstract fun context(context: Context): Builder

        @BindsInstance
        abstract fun baseUrl(@Named("baseUrl") baseUrl: String): Builder
    }
}