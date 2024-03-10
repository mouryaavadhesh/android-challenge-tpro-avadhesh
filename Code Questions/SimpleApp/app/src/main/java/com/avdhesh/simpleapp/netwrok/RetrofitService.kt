package com.avdhesh.simpleapp.network

import com.avdhesh.simpleapp.model.AudioModel
import cz.msebera.httpclient.android.BuildConfig
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitService {
    @GET
    suspend fun getAudio(
    ): Response<AudioModel>
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    var retrofitService: RetrofitService? = null
    private val loggingInterceptor = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun getInstance(): RetrofitService {
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://urvd7g56zh.execute-api.eu-west-2.amazonaws.com/dev")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)
        }
        return retrofitService!!
    }

}