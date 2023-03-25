package com.umc.dodam.src.main.Api

import com.umc.dodam.src.main.myPage.LoginApi.LoginInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var loginApi: LoginInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://43.200.255.191:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        loginApi = retrofit.create(LoginInterface::class.java)
    }
}