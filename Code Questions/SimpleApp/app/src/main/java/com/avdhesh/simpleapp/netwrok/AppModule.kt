package com.avdhesh.simpleapp.network

import AudioRepository
import com.avdhesh.simpleapp.model.AudioViewModelFactory
import com.avdhesh.simpleapp.ui.ApplicationComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitService {
        return RetrofitModule.getInstance();
    }

    @Provides
    @Singleton
    fun provideWeatherViewModelFactory(weatherRepository: AudioRepository): AudioViewModelFactory {
        return AudioViewModelFactory(weatherRepository)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(retrofitService: RetrofitService): AudioRepository {
        return AudioRepository(retrofitService)
    }
}
