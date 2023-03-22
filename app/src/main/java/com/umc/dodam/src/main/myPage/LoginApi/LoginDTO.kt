package com.umc.dodam.src.main.myPage.LoginApi

import com.google.gson.annotations.SerializedName

data class LoginDTO (
    @SerializedName("username")
    var userName: String,

    @SerializedName("password")
    var password: String
)