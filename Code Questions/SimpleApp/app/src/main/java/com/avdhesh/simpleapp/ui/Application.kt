package com.avdhesh.simpleapp.ui

import android.app.Application
import com.avdhesh.simpleapp.network.AppModule
import dagger.Component
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
class SimpleApp : Application()

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(application: SimpleApp)
    // Add more injection methods as needed
}
