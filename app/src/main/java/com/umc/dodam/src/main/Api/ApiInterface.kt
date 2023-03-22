package com.umc.dodam.src.main.Api

import com.umc.dodam.src.main.myPage.LoginApi.LoginDTO
import retrofit2.http.FieldMap
import retrofit2.http.POST

interface ApiInterface {
//    @FormUrlEncoded
    @POST("login")
    fun login(@FieldMap param: LoginDTO)
}