package com.umc.dodam.src.main.myPage.MyPageApi

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("username")
    var userName: String,

    @SerializedName("password")
    var password: String
)

//data class LoginResponse (
//
//        )