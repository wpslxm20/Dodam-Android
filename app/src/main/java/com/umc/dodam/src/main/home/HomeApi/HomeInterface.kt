package com.umc.dodam.src.main.home.HomeApi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface HomeInterface {
    @GET("/step/main")
    fun getStep (
        @Header("Authorization") Authorization: String?
    ): Call<HomeStepResponse>

    @POST("/schedule")
    fun writeSchedule (
        @Body homeScheduleRequest: HomeScheduleRequest
    ) : Call<String>
}