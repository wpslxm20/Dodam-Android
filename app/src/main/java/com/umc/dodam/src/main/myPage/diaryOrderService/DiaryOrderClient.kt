package com.umc.dodam.src.main.myPage.diaryOrderService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DiaryOrderClient {
    private var instance: Retrofit? = null
    private const val CONNECT_TIMEOUT_SEC = 20000L
    private const val BASE_URL = "https://43.200.255.191/"

    fun getInstance() : Retrofit {
        if(instance == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return instance!!
    }
}