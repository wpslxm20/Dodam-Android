package com.umc.dodam.src.main.myPage.MyPageApi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyPageInterface {
    //    @FormUrlEncoded
    @POST("/login")
    fun login(@Body param: LoginRequest) : Call<Void>

}