package com.umc.dodam.src.main.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var api: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://43.200.255.191:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiInterface::class.java)
    }
}