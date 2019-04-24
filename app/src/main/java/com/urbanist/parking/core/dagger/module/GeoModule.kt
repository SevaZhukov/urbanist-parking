package com.urbanist.parking.core.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.location.LocationManager

@Module
class GeoModule {
    @Provides
    @Singleton
    fun provideLocationManager(context: Context) {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    }
}