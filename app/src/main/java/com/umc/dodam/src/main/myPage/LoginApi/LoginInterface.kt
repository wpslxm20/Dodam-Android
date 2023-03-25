package com.umc.dodam.src.main.myPage.LoginApi

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.POST

interface LoginInterface {
    //    @FormUrlEncoded
    @POST("login")
    fun login(@Body param: LoginRequest) : Call<Response<Void>>

}