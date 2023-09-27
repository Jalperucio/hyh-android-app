package com.hiberus.handh.data.feature.services.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hiberus.handh.data.feature.services.remote.serializers.GsonDateDeserializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import java.time.Instant
import java.util.Date
import java.util.concurrent.TimeUnit

interface RickAndMortyService {
    companion object {
        internal const val BASE_API = "https://rickandmortyapi.com/"
        private const val retrofitReadTimeOut = 60L
        private const val retrofitWriteTimeOut = 60L

        fun makeGson(): Gson {
            return GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Date::class.java, GsonDateDeserializer())
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }

        private fun makeLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        fun makeOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor())
            .connectTimeout(retrofitWriteTimeOut, TimeUnit.SECONDS)
            .readTimeout(retrofitReadTimeOut, TimeUnit.SECONDS)
            .build()
    }
}