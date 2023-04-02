package com.umc.dodam.src.main.home.HomeApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeInterface {
    @GET("/step/main")
    fun getStep (
        @Header("Authorization") Authorization: String?
    ): Call<HomeStepResponse>
}