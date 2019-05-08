package com.urbanist.parking.core

import com.urbanist.parking.core.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .context(this)
            .baseUrl("https://8nkn3pvgs0.execute-api.us-east-1.amazonaws.com/prod/")
            .create(this)
}